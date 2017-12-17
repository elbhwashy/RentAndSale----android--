package com.example.rentaandsale.realestate;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentaandsale.realestate.AnnouncementData.Announcement;
import com.example.rentaandsale.realestate.AnnouncementData.AnnouncementDatabaseHelper;
import com.example.rentaandsale.realestate.AnnouncementData.AnnouncementListActivity;

public class AnnouncementActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = AnnouncementActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutTitle;
    private TextInputLayout textInputLayoutPrice;
    private TextInputLayout textInputLayoutType;
    private TextInputLayout textInputLayoutNumberOfRooms;
    private TextInputLayout textInputLayoutFloor;
    private TextInputLayout textInputLayoutDescription;

    private EditText EditTextTitle;
    private EditText EditTextPrice;
    private EditText EditTextType;
    private EditText EditTextNumberOfRooms;
    private EditText EditTextFloor;
    private EditText EditTextDescription;

    private Button ButtonSave;

    private InputValidation inputValidation;
    private AnnouncementDatabaseHelper databaseHelper;
    private Announcement announcement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);



        initViews();
        initListeners();
        initObjects();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                postDataToSQLite();
                Intent intentSave = new Intent(getApplicationContext(), AnnouncementListActivity.class);
                startActivity(intentSave);
                finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollViewAnn);

        textInputLayoutTitle = (TextInputLayout) findViewById(R.id.textInputLayoutTitle);
        textInputLayoutPrice = (TextInputLayout) findViewById(R.id.textInputLayoutPrice);
        textInputLayoutType = (TextInputLayout) findViewById(R.id.textInputLayoutType);
        textInputLayoutNumberOfRooms = (TextInputLayout) findViewById(R.id.textInputLayoutNumberOfRooms);
        textInputLayoutFloor = (TextInputLayout) findViewById(R.id.textInputLayoutFloor);
        textInputLayoutDescription = (TextInputLayout) findViewById(R.id.textInputLayoutDescription);


        EditTextTitle = (EditText) findViewById(R.id.textInputEditTextTitle);
        EditTextPrice = (EditText) findViewById(R.id.textInputEditTextPrice);
        EditTextType = (EditText) findViewById(R.id.textInputEditTextType);
        EditTextNumberOfRooms = (EditText) findViewById(R.id.textInputEditTextNumberOfRooms);
        EditTextFloor = (EditText) findViewById(R.id.textInputEditTextFloor);
        EditTextDescription = (EditText) findViewById(R.id.textInputEditTextDescription);

        ButtonSave = (Button) findViewById(R.id.appCompatButtonSave);


    }

    private void initListeners() {
        ButtonSave.setOnClickListener(this);

    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new AnnouncementDatabaseHelper(activity);
        announcement = new Announcement();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonSave:
                postDataToSQLite();
                Intent intentSave = new Intent(getApplicationContext(), AnnouncementListActivity.class);
                startActivity(intentSave);
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled((TextInputEditText) EditTextTitle, textInputLayoutTitle, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled((TextInputEditText) EditTextPrice, textInputLayoutPrice, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextTitle((TextInputEditText) EditTextTitle, textInputLayoutTitle, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled((TextInputEditText) EditTextType, textInputLayoutType, getString(R.string.error_message_password))) {
            return;
        }

        if (!databaseHelper.checkAnnouncement(EditTextTitle.getText().toString().trim())) {
            announcement.setAnnouncementTitle(EditTextTitle.getText().toString().trim());
            announcement.setAnnouncementPrice(EditTextPrice.getText().toString().trim());
            announcement.setAnnouncementType(EditTextType.getText().toString().trim());
            announcement.setAnnouncementNumberOfRooms(EditTextNumberOfRooms.getText().toString().trim());
            announcement.setAnnouncementFloor(EditTextFloor.getText().toString().trim());
            announcement.setAnnouncementDescription(EditTextDescription.getText().toString().trim());
            databaseHelper.addAnnouncement(announcement);
            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(), getString(R.string.save_message), Toast.LENGTH_LONG).show();
            emptyInputEditText();
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        EditTextTitle.setText(null);
        EditTextPrice.setText(null);
        EditTextType.setText(null);
        EditTextNumberOfRooms.setText(null);
        EditTextFloor.setText(null);
        EditTextDescription.setText(null);

    }
}

