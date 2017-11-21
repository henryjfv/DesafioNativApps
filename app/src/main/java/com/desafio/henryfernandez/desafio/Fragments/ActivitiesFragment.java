package com.desafio.henryfernandez.desafio.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.desafio.henryfernandez.desafio.Helper.DataBaseHelper;
import com.desafio.henryfernandez.desafio.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 20/11/17.
 */

public class ActivitiesFragment extends Fragment {

    RecyclerView recyclerViewSearchlable;
    public static DataBaseHelper db;
    MyAdapter mAdapter;
    Calendar dateTime = Calendar.getInstance();
    EditText edtxtOrganizations, edtxtPersons, editTextEndDate, editTextDeal, editTextHour;
    AlertDialog dialog, dialog2;
    DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy");
    DateFormat ndf2 = new SimpleDateFormat("hh:mm a");
    DateFormat ndfhora = new SimpleDateFormat("HH:mm");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activities, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArraysSingleton.getInstance().ArraySingleton = new ArrayList<>();

        final EditText descriptionEditText = (EditText) view.findViewById(R.id.descriptionActivit);
        final EditText typeEditText = (EditText) view.findViewById(R.id.typeActivity);

        edtxtOrganizations = (EditText) view.findViewById(R.id.organizationActivity);
        edtxtOrganizations.setFocusable(false);
        edtxtOrganizations.setKeyListener(null);
        edtxtOrganizations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getAllDataOrganizations();
                //StringBuffer stringBuffer = new StringBuffer();
                if (cursor != null && cursor.getCount() > 0) {
                    ArrayList<moduleDefault> dataSet = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        dataSet.add(new moduleDefault(0, cursor.getString(1),
                                cursor.getString(2) + ", " +
                                        cursor.getString(3)));
                                /*stringBuffer.append("Id: "+ cursor.getString(0)+"\n");
                                stringBuffer.append("Name: "+ cursor.getString(1)+"\n");
                                stringBuffer.append("Phone: "+ cursor.getString(2)+"\n");
                                stringBuffer.append("Email: "+ cursor.getString(3)+"\n");*/
                    }
                    showAlertSearchlable(dataSet);
                } else {
                    Fragment mFragment = new OrganizationFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.screen_area, mFragment);
                    ft.commit();
                }
            }
        });

        edtxtPersons = (EditText) view.findViewById(R.id.personActivity);
        edtxtPersons.setFocusable(false);
        edtxtPersons.setKeyListener(null);
        edtxtPersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getAllDataPersons();
                //StringBuffer stringBuffer = new StringBuffer();
                if (cursor != null && cursor.getCount() > 0) {
                    ArrayList<moduleDefault> dataSet = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        dataSet.add(new moduleDefault(1, cursor.getString(1), cursor.getString(2) + ", " +
                                cursor.getString(3)));
                                /*stringBuffer.append("Id: "+ cursor.getString(0)+"\n");
                                stringBuffer.append("Name: "+ cursor.getString(1)+"\n");
                                stringBuffer.append("Phone: "+ cursor.getString(2)+"\n");
                                stringBuffer.append("Email: "+ cursor.getString(3)+"\n");*/
                    }
                    showAlertSearchlable(dataSet);
                }else{
                    Fragment mFragment = new PersonFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.screen_area, mFragment);
                    ft.commit();
                }

            }
        });

        editTextDeal = (EditText) view.findViewById(R.id.dealActivity);
        editTextDeal.setFocusable(false);
        editTextDeal.setKeyListener(null);
        editTextDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getAllDataDeal();
                //StringBuffer stringBuffer = new StringBuffer();
                if (cursor != null && cursor.getCount() > 0) {
                    ArrayList<moduleDefault> dataSet = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        dataSet.add(new moduleDefault(2, cursor.getString(1), cursor.getString(5) + ", " +
                                cursor.getString(6)));
                                /*stringBuffer.append("Id: "+ cursor.getString(0)+"\n");
                                stringBuffer.append("Name: "+ cursor.getString(1)+"\n");
                                stringBuffer.append("Phone: "+ cursor.getString(2)+"\n");
                                stringBuffer.append("Email: "+ cursor.getString(3)+"\n");*/
                    }
                    showAlertSearchlable(dataSet);
                }else{
                    Fragment mFragment = new DealFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.screen_area, mFragment);
                    ft.commit();
                }
            }
        });

        editTextEndDate = (EditText) view.findViewById(R.id.dateActivity);
        editTextEndDate.setText(format.format(dateTime.getTime()));
        editTextEndDate.setFocusable(false);
        editTextEndDate.setKeyListener(null);
        editTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editTextHour = (EditText) view.findViewById(R.id.hourActivity);
        editTextHour.setText(ndf2.format(dateTime.getTime()));
        editTextHour.setFocusable(false);
        editTextHour.setKeyListener(null);
        editTextHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(getContext(), t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_activity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descripcion = descriptionEditText.getText().toString();
                String type = typeEditText.getText().toString();
                String organization = edtxtOrganizations.getText().toString();
                String person = edtxtPersons.getText().toString();
                String deal = editTextDeal.getText().toString();
                String date = editTextEndDate.getText().toString();
                String hour = editTextHour.getText().toString();

                if (!type.isEmpty() && !descripcion.isEmpty()) {
                    boolean result = db.insertDataActivity(descripcion.trim(), type.trim(), organization.trim(),
                            person, deal, date, hour);
                    if (result) {
                        Fragment mFragment = new ListActivityFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction ft= fragmentManager.beginTransaction();
                        ft.replace(R.id.screen_area,mFragment);
                        ft.commit();
                    } else {
                        Toast.makeText(getActivity(), "Data Insert Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (descripcion.isEmpty()) {
                        descriptionEditText.setError("Field can't be empty");
                    } else if (type.isEmpty()) {
                        typeEditText.setError("Field can't be empty");
                    }
                }
            }
        });
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            SimpleDateFormat ndfOriginal = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            Date fechaParseada = null;
            try {
                fechaParseada = ndfOriginal.parse(fecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (fechaParseada != null)
                editTextEndDate.setText(format.format(fechaParseada).toString());
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            String hora = String.valueOf(hourOfDay);
            String minuto = String.valueOf(minute);
            if (Integer.parseInt(hora) < 10) {
                hora = "0" + hourOfDay;
            }
            if (Integer.parseInt(minuto) < 10) {
                minuto = "0" + minute;
            }
            Date horaParseada = null;
            try {
                horaParseada = ndfhora.parse(hora + ":" + minuto);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (horaParseada != null)
                editTextHour.setText(ndf2.format(horaParseada));

        }
    };

    public void showAlertSearchlable(final ArrayList<moduleDefault> populateDataSet) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater().inflate(R.layout.serchlable_alert_dialog, null);
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

                for (moduleDefault item : populateDataSet) {
                    String name = item.getTitle();
                    if (name.contains(s)) {
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
        dialog = mBuilder.create();
        dialog.show();
    }

    public class moduleDefault {
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
                mTxtTitle = (TextView) v.findViewById(R.id.namePersonItem);
                mTitleSubtitle = (TextView) v.findViewById(R.id.phoneEmailPersonItem);
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
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTxtTitle.setText(mDataset.get(position).getTitle());
            holder.mTitleSubtitle.setText(mDataset.get(position).getSubtitle());

            holder.mLn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArraysSingleton.getInstance().ArraySingleton.add(mDataset.get(position));
                    String valuesO = "";
                    String valuesP = "";
                    String valuesD = "";
                    for (moduleDefault item : ArraysSingleton.getInstance().ArraySingleton) {
                        if (item.getPlace() == 0) {
                            valuesO = item.getTitle();
                        } else if (item.getPlace() == 1) {
                            valuesP = item.getTitle();
                        } else if (item.getPlace() == 2) {
                            valuesD = item.getTitle();
                        }
                    }
                    if (!valuesO.isEmpty()) {
                        edtxtOrganizations.setText(valuesO);
                    }
                    if (!valuesP.isEmpty()) {
                        edtxtPersons.setText(valuesP);
                    }
                    if (!valuesD.isEmpty()) {
                        editTextDeal.setText(valuesD);
                    }
                    dialog.dismiss();
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public void setFilter(ArrayList<moduleDefault> newList) {
            mDataset = new ArrayList<>();
            mDataset.addAll(newList);
            notifyDataSetChanged();
        }
    }

    public void populateDataSet(ArrayList<moduleDefault> myDataset) {
        mAdapter = new MyAdapter(myDataset);
        recyclerViewSearchlable.setAdapter(mAdapter);
    }

    public static class ArraysSingleton {

        public ArrayList<moduleDefault> ArraySingleton;

        private static ArraysSingleton ourInstance = new ArraysSingleton();

        public static ArraysSingleton getInstance() {
            if (ourInstance == null) {
                ourInstance = new ArraysSingleton();
            }
            return ourInstance;
        }
    }
}
