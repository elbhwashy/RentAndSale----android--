package com.example.rentaandsale.realestate.AnnouncementData;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.rentaandsale.realestate.AnnouncementActivity;
import com.example.rentaandsale.realestate.MainActivity;
import com.example.rentaandsale.realestate.R;

import java.util.ArrayList;
import java.util.List;



public class AnnouncementListActivity extends AppCompatActivity {
    private AppCompatActivity activity = AnnouncementListActivity.this;
    private TextView textViewTitle;
    private RecyclerView recyclerViewAnnouncement;
    private List<Announcement> listAnnouncements;
    private AnnouncementAdapter announcementRecyclerAdapter;
    private AnnouncementDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_list);
        getSupportActionBar().setTitle("");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnnouncementListActivity.this, AnnouncementActivity.class);
                startActivity(intent);
            }
        });
        recyclerViewAnnouncement = findViewById(R.id.recyclerViewAnnouncement);
        //recyclerViewAnnouncement.setOnClickListener(new );



                initViews();
        initObjects();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_announcement_data:
                Intent intent = new Intent(AnnouncementListActivity.this, AnnouncementActivity.class);
                startActivity(intent);
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_home:
                Intent intenth = new Intent(AnnouncementListActivity.this, MainActivity.class);
                startActivity(intenth);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        textViewTitle = (TextView) findViewById(R.id.textViewTilte);
        recyclerViewAnnouncement = (RecyclerView) findViewById(R.id.recyclerViewAnnouncement);
    }

    private void initObjects() {
        listAnnouncements = new ArrayList<>();
        announcementRecyclerAdapter = new AnnouncementAdapter((ArrayList<Announcement>) listAnnouncements,this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAnnouncement.setLayoutManager(mLayoutManager);
        recyclerViewAnnouncement.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAnnouncement.setHasFixedSize(true);
        recyclerViewAnnouncement.setAdapter(announcementRecyclerAdapter);
        databaseHelper = new AnnouncementDatabaseHelper(activity);

//        String emailFromIntent = getIntent().getStringExtra("EMAIL");
//        textViewName.setText(emailFromIntent);

        getDataFromSQLite();
    }
    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listAnnouncements.clear();
                listAnnouncements.addAll(databaseHelper.getAllAnnouncement());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                announcementRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
