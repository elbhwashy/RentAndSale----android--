package com.example.rentaandsale.realestate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rentaandsale.realestate.AnnouncementData.AnnouncementGuestListActivity;
import com.example.rentaandsale.realestate.AnnouncementData.AnnouncementListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView logInAsGuest = (TextView) findViewById(R.id.textView_guest);
        logInAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent signInIntent = new Intent(MainActivity.this, AnnouncementGuestListActivity.class);



                startActivity(signInIntent);
            }
        });
        TextView signIn = (TextView) findViewById(R.id.textView_sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent signInIntent = new Intent(MainActivity.this, LogInActivity.class);



                startActivity(signInIntent);
            }
        });
        TextView signUp = (TextView) findViewById(R.id.textView_sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent signUpIntent = new Intent(MainActivity.this, RegisterActivity.class);



                startActivity(signUpIntent);
            }
        });
    }
}
