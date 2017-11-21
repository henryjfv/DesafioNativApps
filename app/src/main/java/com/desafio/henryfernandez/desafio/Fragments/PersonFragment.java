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

public class PersonFragment extends Fragment {

    public static DataBaseHelper db;
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    RecyclerView rcv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText nameEdt =  (EditText) view.findViewById(R.id.namePerson);
        final EditText phoneEdt =  (EditText) view.findViewById(R.id.phoneNumer);
        final EditText emailEdt =  (EditText) view.findViewById(R.id.email);
        rcv = (RecyclerView) view.findViewById(R.id.recyclerPerson);
        rcv.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(mLayoutManager);

        Cursor cursor = db.getAllDataPersons();
        //StringBuffer stringBuffer = new StringBuffer();
        if(cursor != null && cursor.getCount() > 0){
            ArrayList<Person> dataSet = new ArrayList<>();
            while (cursor.moveToNext()){
                dataSet.add(new Person(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3)));
                                /*stringBuffer.append("Id: "+ cursor.getString(0)+"\n");
                                stringBuffer.append("Name: "+ cursor.getString(1)+"\n");
                                stringBuffer.append("Phone: "+ cursor.getString(2)+"\n");
                                stringBuffer.append("Email: "+ cursor.getString(3)+"\n");*/
            }
            populateDataSet(dataSet);
            //Toast.makeText(getActivity(),stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        }

        view.findViewById(R.id.fab_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                String email = emailEdt.getText().toString();

                if(!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()){
                    boolean result = db.insertData(name.trim(),phone.trim(),email.trim());
                    if(result){
                        nameEdt.setText("");
                        phoneEdt.setText("");
                        emailEdt.setText("");
                        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                        Cursor cursor = db.getAllDataPersons();
                        //StringBuffer stringBuffer = new StringBuffer();
                        if(cursor != null && cursor.getCount() > 0){
                            ArrayList<Person> dataSet = new ArrayList<>();
                            while (cursor.moveToNext()){
                                dataSet.add(new Person(cursor.getString(1), cursor.getString(2),
                                        cursor.getString(3)));
                                /*stringBuffer.append("Id: "+ cursor.getString(0)+"\n");
                                stringBuffer.append("Name: "+ cursor.getString(1)+"\n");
                                stringBuffer.append("Phone: "+ cursor.getString(2)+"\n");
                                stringBuffer.append("Email: "+ cursor.getString(3)+"\n");*/
                            }
                            populateDataSet(dataSet);
                            //Toast.makeText(getActivity(),stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "No data to retrive", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Data Insert Failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(name.isEmpty()){
                        nameEdt.setError("Field can't be empty");
                    }else if (phone.isEmpty()){
                        phoneEdt.setError("Field can't be empty");
                    }else if(email.isEmpty()){
                        emailEdt.setError("Field can't be empty");
                    }
                }
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<Person> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextViewName, mTextViewPhoneEmail;
            public ViewHolder(View v) {
                super(v);
                mTextViewName =(TextView) v.findViewById(R.id.namePersonItem);
                mTextViewPhoneEmail =(TextView) v.findViewById(R.id.phoneEmailPersonItem);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<Person> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextViewName.setText(mDataset.get(position).getName());
            holder.mTextViewPhoneEmail.setText(mDataset.get(position).getPhone() +
            ", "+ mDataset.get(position).getEmail());

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    public void populateDataSet(ArrayList<Person> myDataset){
        mAdapter = new MyAdapter(myDataset);
        rcv.setAdapter(mAdapter);
    }
}
