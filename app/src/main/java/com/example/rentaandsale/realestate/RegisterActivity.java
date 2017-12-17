package com.example.rentaandsale.realestate;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rentaandsale.realestate.AnnouncementData.AnnouncementListActivity;
import com.example.rentaandsale.realestate.UserData.User;
import com.example.rentaandsale.realestate.UserData.UserDatabaseHelper;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private EditText EditTextName;
    private EditText EditTextEmail;
    private EditText EditTextPassword;
    private EditText EditTextConfirmPassword;

    private Button ButtonRegister;
    private TextView TextViewLoginLink;

    private InputValidation inputValidation;
    private UserDatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        EditTextName = (EditText) findViewById(R.id.textInputEditTextName);
        EditTextEmail = (EditText) findViewById(R.id.textInputEditTextEmail);
        EditTextPassword = (EditText) findViewById(R.id.textInputEditTextPassword);
        EditTextConfirmPassword = (EditText) findViewById(R.id.textInputEditTextConfirmPassword);

        ButtonRegister = (Button) findViewById(R.id.appCompatButtonRegister);

        TextViewLoginLink = (TextView) findViewById(R.id.appCompatTextViewLoginLink);

    }


    private void initListeners() {
        ButtonRegister.setOnClickListener(this);
        TextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new UserDatabaseHelper(activity);
        user = new User();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                Intent loginIntent = new Intent(activity, LogInActivity.class);
                startActivity(loginIntent);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled((TextInputEditText) EditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled((TextInputEditText) EditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail((TextInputEditText) EditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled((TextInputEditText) EditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches((TextInputEditText)EditTextPassword, (TextInputEditText)EditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(EditTextEmail.getText().toString().trim())) {

            user.setUserName(EditTextName.getText().toString().trim());
            user.setUserEmail(EditTextEmail.getText().toString().trim());
            user.setUserPassword(EditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        EditTextName.setText(null);
        EditTextEmail.setText(null);
        EditTextPassword.setText(null);
        EditTextConfirmPassword.setText(null);
    }
}

