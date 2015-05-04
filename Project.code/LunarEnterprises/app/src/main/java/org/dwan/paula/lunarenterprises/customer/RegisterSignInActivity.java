package org.dwan.paula.lunarenterprises.customer;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    RegisterSignInActivity.java
            register customers sign-in details and also adds customer contact detail to
            SQLite database.
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.dwan.paula.lunarenterprises.R;
import org.dwan.paula.lunarenterprises.database.Customer;
import org.dwan.paula.lunarenterprises.database.DatabaseAdapter;
import org.dwan.paula.lunarenterprises.database.DatabaseError;

import java.sql.SQLException;

/**
 * New Customer can now register to use the app.
 * <ul>
 * <li>[Submit] Register login details and customer contact details and preferences to SQL database.</li>
 * <li>[Clear All] reset all <i>\<EditText\></i> fields to default value. </li>
 * </ul>
 */
public class RegisterSignInActivity extends ActionBarActivity {

    private static final String CLASS_NAME = "RegisterSignInActivity";

    EditText etNewUser, etNewPassword, etConfirmNewPassword;
    Button btnRegisterSubmit;
    DatabaseAdapter databaseAdapter;
    Customer customer = new Customer();

    /**
     * onCreate : register new user layout
     * <ul>
     * <li>open database, </li>
     * <li>source from new user the proposed user name and password,</li>
     * <li>validate proposed user name and password,</li> </ul>
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "\t: onCreate - register new user ...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sign_in);

        Intent registerIntent = getIntent();
        populateCustomerDetailsFields(registerIntent);
    }


    /**
     * Delete / Clear all customer details : handle Click Event of <b>Clear All</b> button,
     * basically reset all fields.
     *
     * @param view
     */
    public void clearAllRegisterLoginDetails(View view) {
        Log.d(CLASS_NAME, "\t: clear all customer login details as entered ...");

        etNewUser.getText().clear();
        etNewPassword.getText().clear();
        etConfirmNewPassword.getText().clear();
    }

    /**
     * Register customer details : handle Click Event of <b>Submit</b> button,
     * <ul>
     * <li>validate user name - not Null, does not match those in database</li>
     * <li>validate password - not Null, both original and confirmed match, does not match those in database</li>
     * <li>adds customer information as passed, user name and password to SQL database</li>
     * </ul>
     *
     * @param view
     */
    public void registerCustomerSQLDetails(View view) {
        Log.d(CLASS_NAME, "\t: validate & register new customer in SQL database ...");

        databaseAdapter = new DatabaseAdapter(this);
        try {
            databaseAdapter.open();
        } catch (SQLException e) {
            Log.d(CLASS_NAME, "\t: onCreate - register new user - SQL exception...");
            e.printStackTrace();
        }
        databaseAdapter.createCustomer(customer);
    }

    private void populateCustomerDetailsFields(Intent intent) {
        Log.d(CLASS_NAME, "\t: populate EditFields using customer login details as entered ...");

        etNewUser = (EditText) findViewById(R.id.edittextRegisterUser);
        etNewPassword = (EditText) findViewById(R.id.edittextPasswordRegister);

        if (intent != null) {
            customer.setName1(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_NAME1));
            customer.setName2(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_NAME2));
            customer.setAddress1(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_ADDRESS1));
            customer.setAddress2(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_ADDRESS2));
            customer.setAddress3(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_ADDRESS3));
            customer.setAddress4(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_ADDRESS4));
            customer.setMobile(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_MOBILE));
            customer.setEmail(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_EMAIL));
            customer.setEmailOk(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_EMAIL_OKAY));
            customer.setMobileOk(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_MOBILE_OKAY));
            customer.setThirdPartyOk(intent.getStringExtra(RegisterCustomerDetailsActivity.CUST_3PARTY_OKAY));
        }

        btnRegisterSubmit = (Button) findViewById(R.id.buttonSignInSubmit);
        btnRegisterSubmit.setEnabled(false);

        if (validateAnyEditFieldUsed(new EditText[]{etNewUser, etNewPassword})) {
            if (validateActivityFields()) {
                customer.setNameLogin(etNewUser.getText().toString());
                customer.setPasswordLogin(etNewPassword.getText().toString());
                btnRegisterSubmit.setEnabled(true);
            }
        }
    }

    /**
     * onDestroy - register new user layout, close database
     */
    @Override
    protected void onDestroy() {
        Log.d(CLASS_NAME, "\t: onDestroy - close database ...");
        super.onDestroy();
        databaseAdapter.close();
    }

    /**
     * Creates action bar for inclusion of overflow menu.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(CLASS_NAME, "\t: onCreateOptionsMenu - Action Bar ...");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.app_logo);
        actionBar.setDisplayUseLogoEnabled(true);

        return true;
    }

    /**
     * Create and populate overflow menu in Action Bar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(CLASS_NAME, "\t: onOptionsItemSelected - Action Bar ...");

        switch (item.getItemId()) {
            case R.id.overflow_close:
                onDestroy();
                break;
            case R.id.overflow_deregister:
                // DatabaseAdapter databaseAdapter ;
                // DeRegisterCustomer deRegisterCustomer = new DeRegisterCustomer();
                // DeRegisterCustomer.removeCustomerFromTable(userName);
                Toast.makeText(this, CustomerError.ERROR9001, Toast.LENGTH_SHORT).show();
                break;
            case R.id.overflow_help:
                Toast.makeText(this, CustomerError.ERROR9002, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    //
    // VALIDATION METHODS
    //

    private boolean validateActivityFields() {
        boolean isNotValid = false;

        int maxLength = 40;
        final String REGEX_SIGN_IN = "[a-z, 0-9]{6,40}";

        String userName = etNewUser.getText().toString();
        String userPasswordCreate = etNewPassword.getText().toString();
        String userPasswordConfirmed = etConfirmNewPassword.getText().toString();

        if (exceedsMaximumLength(userName.length(), maxLength)) {
            etNewUser.setError(CustomerError.ERROR8001);
            clearEditField(etNewUser);
            isNotValid = true;
        } else if (isNullField(userName.length())) {
            etNewUser.setError(CustomerError.ERROR4001);
            clearEditField(etNewUser);
            isNotValid = true;
        } else if (isNotValidFormatRegex(userName, REGEX_SIGN_IN)) {
            etNewUser.setError(CustomerError.ERROR3001);
            clearEditField(etNewUser);
            isNotValid = true;
        } else if (databaseAdapter.isUserPresent(userName)) {
            etNewUser.setError(DatabaseError.ERROR2002);
            clearEditField(etNewUser);
            isNotValid = true;
        } else {
            etNewUser.setError(null);
            isNotValid = false;
        }
        if (exceedsMaximumLength(userPasswordCreate.length(), maxLength)) {
            etNewPassword.setError(CustomerError.ERROR8001);
            clearEditField(etNewPassword);
            isNotValid = true;
        } else if (isNullField(userPasswordCreate.length())) {
            etNewPassword.setError(CustomerError.ERROR4001);
            clearEditField(etNewPassword);
            isNotValid = true;
        } else if (!userPasswordCreate.equals(userPasswordConfirmed)) {
            etNewPassword.setError(CustomerError.ERROR3005);
            clearEditField(etNewPassword);
            clearEditField(etConfirmNewPassword);
            isNotValid = true;
        } else {
            etNewPassword.setError(null);
            isNotValid = false;
        }
        if (isNullField(userPasswordConfirmed.length())) {
            etNewPassword.setError(CustomerError.ERROR4001);
            clearEditField(etNewPassword);
            isNotValid = true;
        } else {
            etNewPassword.setError(null);
            isNotValid = false;
        }
        return isNotValid;
    }

    private boolean validateAnyEditFieldUsed(EditText[] allEditFields) {
        for (int i = 0; i < allEditFields.length; i++) {
            EditText currentEditField = allEditFields[i];
            return (currentEditField.getText().toString().length() > 0) ? true : false;
        }
        return false;
    }

    private boolean exceedsMaximumLength(int fieldLength, int maxLength) {
        return (fieldLength > maxLength);
    }

    private boolean isNullField(int fieldLength) {
        return (fieldLength == 0);
    }


    private boolean isNotValidFormatRegex(String fieldValue, String fieldRegex) {
        return fieldValue.matches(fieldRegex);
    }

    private void clearEditField(EditText et) {
        et.getText().clear();
    }

}