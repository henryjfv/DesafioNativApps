package com.desafio.henryfernandez.desafio;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.desafio.henryfernandez.desafio.Fragments.ActivitiesFragment;
import com.desafio.henryfernandez.desafio.Fragments.DealFragment;
import com.desafio.henryfernandez.desafio.Fragments.HomeFragment;
import com.desafio.henryfernandez.desafio.Fragments.ListActivityFragment;
import com.desafio.henryfernandez.desafio.Fragments.ListDealsFragment;
import com.desafio.henryfernandez.desafio.Fragments.OrganizationFragment;
import com.desafio.henryfernandez.desafio.Fragments.PersonFragment;
import com.desafio.henryfernandez.desafio.Helper.DataBaseHelper;

//4:30-5:30//8:30-1:30//8:30-11:00//1:30

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment mFragment;
    Context mContext;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        db = new DataBaseHelper(mContext);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragment = new HomeFragment();
        PersonFragment.db = db;
        OrganizationFragment.db = db;
        DealFragment.db = db;
        ListDealsFragment.db = db;
        ActivitiesFragment.db = db;
        ListActivityFragment.db = db;
        setFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            mFragment = new HomeFragment();
        } else if (id == R.id.nav_people) {
            mFragment = new PersonFragment();
        } else if (id == R.id.nav_organization) {
            mFragment = new OrganizationFragment();
        } else if (id == R.id.nav_deal) {
            mFragment = new DealFragment();
        } else if (id == R.id.nav_activities) {
            mFragment = new ActivitiesFragment();
        } else if(id == R.id.nav_list_deals){
            mFragment = new ListDealsFragment();
        }else if(id == R.id.nav_list_acitivities){
            mFragment = new ListActivityFragment();
        }

        setFragment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(){
        if(mFragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft= fragmentManager.beginTransaction();
            ft.replace(R.id.screen_area,mFragment);
            ft.commit();
        }

    }
}
