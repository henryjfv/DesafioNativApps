package com.desafio.henryfernandez.desafio.Fragments;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.desafio.henryfernandez.desafio.Helper.DataBaseHelper;
import com.desafio.henryfernandez.desafio.R;

import java.util.ArrayList;

/**
 * Created by user on 20/11/17.
 */

public class DealFragment extends Fragment {

    RecyclerView recyclerViewSearchlable;
    public static DataBaseHelper db;
    MyAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deal,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText edtxtOrganizations = (EditText) view.findViewById(R.id.organizationsDeal);
        EditText edtxtPersons = (EditText) view.findViewById(R.id.personsDeal);
        edtxtOrganizations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getAllDataOrganizations();
                //StringBuffer stringBuffer = new StringBuffer();
                if(cursor != null && cursor.getCount() > 0){
                    ArrayList<moduleDefault> dataSet = new ArrayList<>();
                    while (cursor.moveToNext()){
                        dataSet.add(new moduleDefault(cursor.getString(1), cursor.getString(2) + " "+
                                cursor.getString(3)));
                                /*stringBuffer.append("Id: "+ cursor.getString(0)+"\n");
                                stringBuffer.append("Name: "+ cursor.getString(1)+"\n");
                                stringBuffer.append("Phone: "+ cursor.getString(2)+"\n");
                                stringBuffer.append("Email: "+ cursor.getString(3)+"\n");*/
                    }
                    showAlertSearchlable(dataSet);
                }
            }
        });
        edtxtPersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getAllDataPersons();
                //StringBuffer stringBuffer = new StringBuffer();
                if(cursor != null && cursor.getCount() > 0){
                    ArrayList<moduleDefault> dataSet = new ArrayList<>();
                    while (cursor.moveToNext()){
                        dataSet.add(new moduleDefault(cursor.getString(1), cursor.getString(2) + " "+
                                cursor.getString(3)));
                                /*stringBuffer.append("Id: "+ cursor.getString(0)+"\n");
                                stringBuffer.append("Name: "+ cursor.getString(1)+"\n");
                                stringBuffer.append("Phone: "+ cursor.getString(2)+"\n");
                                stringBuffer.append("Email: "+ cursor.getString(3)+"\n");*/
                    }
                    showAlertSearchlable(dataSet);
                }

            }
        });
    }
    public void showAlertSearchlable(final ArrayList<moduleDefault> populateDataSet){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater().inflate(R.layout.serchlable_alert_dialog,null);
        recyclerViewSearchlable = (RecyclerView) mView.findViewById(R.id.recyclerSearlable);
        recyclerViewSearchlable.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewSearchlable.setLayoutManager(mLayoutManager);
        populateDataSet(populateDataSet);

        EditText editTextSearch = (EditText) mView.findViewById(R.id.editTextSearchable);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                ArrayList<moduleDefault> newList = new ArrayList<>();

                for(moduleDefault item: populateDataSet){
                    String name = item.getTitle();
                    if(name.contains(s)){
                        newList.add(item);
                    }
                }
                mAdapter.setFilter(newList);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    public class moduleDefault{
        String title;
        String subtitle;

        public moduleDefault(String title, String subtitle) {
            this.title = title;
            this.subtitle = subtitle;
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

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<moduleDefault> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTxtTitle, mTitleSubtitle;
            public ViewHolder(View v) {
                super(v);
                mTxtTitle =(TextView) v.findViewById(R.id.namePersonItem);
                mTitleSubtitle =(TextView) v.findViewById(R.id.phoneEmailPersonItem);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<moduleDefault> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new MyAdapter.ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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

        public void setFilter(ArrayList<moduleDefault> newList){
            mDataset = new ArrayList<>();
            mDataset.addAll(newList);
            notifyDataSetChanged();
        }
    }

    public void populateDataSet(ArrayList<moduleDefault> myDataset){
        mAdapter = new MyAdapter(myDataset);
        recyclerViewSearchlable.setAdapter(mAdapter);
    }
}
