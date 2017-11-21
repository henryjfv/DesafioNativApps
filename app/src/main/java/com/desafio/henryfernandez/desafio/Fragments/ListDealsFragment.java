package com.desafio.henryfernandez.desafio.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.desafio.henryfernandez.desafio.Helper.DataBaseHelper;
import com.desafio.henryfernandez.desafio.Models.Person;
import com.desafio.henryfernandez.desafio.R;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by user on 20/11/17.
 */

public class ListDealsFragment extends Fragment {

    public static DataBaseHelper db;
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    RecyclerView rcv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_deal,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = (RecyclerView) view.findViewById(R.id.recyclerListDeal);
        rcv.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(mLayoutManager);

        Cursor cursor = db.getAllDataDeal();
        //StringBuffer stringBuffer = new StringBuffer();
        if(cursor != null && cursor.getCount() > 0){
            ArrayList<moduleDefault> dataSet = new ArrayList<>();
            while (cursor.moveToNext()){
                dataSet.add(new moduleDefault(3,cursor.getString(1), cursor.getString(2) + " " +
                        cursor.getString(3)));
                                /*stringBuffer.append("Id: "+ cursor.getString(0)+"\n");
                                stringBuffer.append("Name: "+ cursor.getString(1)+"\n");
                                stringBuffer.append("Phone: "+ cursor.getString(2)+"\n");
                                stringBuffer.append("Email: "+ cursor.getString(3)+"\n");*/
            }
            populateDataSet(dataSet);
            //Toast.makeText(getActivity(),stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<moduleDefault> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTxtTitle, mTitleSubtitle;
            LinearLayout mLn;
            public ViewHolder(View v) {
                super(v);
                mTxtTitle =(TextView) v.findViewById(R.id.namePersonItem);
                mTitleSubtitle =(TextView) v.findViewById(R.id.phoneEmailPersonItem);
                mLn = (LinearLayout) v.findViewById(R.id.LinearItemContainer);
            }
        }


        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<moduleDefault> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
            // set the view's size, margins, paddings and layout parameters

            MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTxtTitle.setText(mDataset.get(position).getTitle());
            holder.mTitleSubtitle.setText(mDataset.get(position).getSubtitle());

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

    }

    public void populateDataSet(ArrayList<moduleDefault> myDataset){
        mAdapter = new MyAdapter(myDataset);
        rcv.setAdapter(mAdapter);
    }

    public class moduleDefault{
        int place;
        String title;
        String subtitle;

        public moduleDefault(int place, String title, String subtitle) {
            this.place = place;
            this.title = title;
            this.subtitle = subtitle;
        }


        public int getPlace() {
            return place;
        }

        public void setPlace(int place) {
            this.place = place;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }
    }
}
