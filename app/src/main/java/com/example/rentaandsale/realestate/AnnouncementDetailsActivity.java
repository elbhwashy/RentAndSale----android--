package com.example.rentaandsale.realestate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class AnnouncementDetailsActivity extends AppCompatActivity {
    TextView textViewTitle;
    TextView textViewPrice;
    TextView textViewType;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_details);

        textViewTitle = (TextView)findViewById(R.id.text_view_title);
        textViewPrice = (TextView)findViewById(R.id.text_view_price);
        textViewType = (TextView)findViewById(R.id.text_view_type);
        textViewTitle.setText(" "+getIntent().getStringExtra("announcementTitle"));
        textViewPrice.setText(" "+getIntent().getStringExtra("announcementPrice"));
        textViewType.setText(" "+getIntent().getStringExtra(  "announcementType"));
    }
}
