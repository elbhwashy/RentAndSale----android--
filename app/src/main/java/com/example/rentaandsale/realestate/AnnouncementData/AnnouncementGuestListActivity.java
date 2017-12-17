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
import android.view.View;
import android.widget.TextView;

import com.example.rentaandsale.realestate.AnnouncementActivity;
import com.example.rentaandsale.realestate.R;

import java.util.ArrayList;
import java.util.List;



public class AnnouncementGuestListActivity extends AppCompatActivity {
    private AppCompatActivity activity = AnnouncementGuestListActivity.this;
    private TextView textViewTitle;
    private RecyclerView recyclerViewAnnouncement;
    private List<Announcement> listAnnouncements;
    private AnnouncementAdapter announcementRecyclerAdapter;
    private AnnouncementDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_guest_list);
        getSupportActionBar().setTitle("");

        initViews();
        initObjects();

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
