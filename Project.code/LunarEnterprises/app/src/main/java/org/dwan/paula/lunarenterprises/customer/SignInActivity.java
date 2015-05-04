package org.dwan.paula.lunarenterprises.customer;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    SignInActivity.java
            Existing user signs into app using existing login name and password.
            Both are validated against the customers.tb in the lunar.db.
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

import org.dwan.paula.lunarenterprises.GlobalVariableCustomerId;
import org.dwan.paula.lunarenterprises.R;
import org.dwan.paula.lunarenterprises.database.Customer;
import org.dwan.paula.lunarenterprises.database.DatabaseAdapter;
import org.dwan.paula.lunarenterprises.database.DatabaseError;
import org.dwan.paula.lunarenterprises.store.DisplayStoreListingActivity;

import java.sql.SQLException;

public class SignInActivity extends ActionBarActivity {

    private static final String CLASS_NAME = "SignInActivity";

    DatabaseAdapter databaseAdapter;
    EditText etUser, etUserPassword;
    Button btnSignInSubmit;

    /**
     * onCreate : verify existing user layout
     * <ul>
     * <li>open database, </li>
     * <li>source from existing user his/her user name and password,</li>
     * <li>validate user name and password against those in database,</li>
     * </ul>
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "\t: onCreate --> validate existing user - SQL exception ...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        databaseAdapter = new DatabaseAdapter(this);
        try {
            databaseAdapter = databaseAdapter.open();
        } catch (SQLException e) {
            Log.e(CLASS_NAME, "\t: onCreate SQL exception for DatabaseAdapter ...");
            e.printStackTrace();
        }

        populateActivityFields();
    }

    /**
     * delete / clear all customer details : handle Click Event of <b>Clear All</b> button,
     * basically reset all fields.
     *
     * @param view
     */
    public void clearAllSignInDetails(View view) {
        Log.d(CLASS_NAME, "\t: clear all sign-in details as entered by existing customer ...");

        etUser.getText().clear();
        etUserPassword.getText().clear();
    }

    /**
     * delete / clear all customer details : handle Click Event of <b>Clear All</b> button,
     * basically reset all fields.
     *
     * @param view
     */
    public void submitCustomerSQLDetails(View view) {
        Log.d(CLASS_NAME, "\t: validate sign-in details as entered by existing customer & open next activity...");

        Customer customer = new Customer();
        final GlobalVariableCustomerId globalVariableCustomerId = (GlobalVariableCustomerId) getApplicationContext();
        customer = databaseAdapter.getCustomerUsingUserName(etUser.getText().toString());
        globalVariableCustomerId.setCustId(customer.getId());

        Intent intentSignInDetails = new Intent(getApplicationContext(), DisplayStoreListingActivity.class);
        startActivity(intentSignInDetails);
    }


    /**
     * onDestroy - register new user layout, close database
     */
    @Override
    protected void onDestroy() {
        Log.d(CLASS_NAME, "\t: onDestroy - close database & finish ...");
        super.onDestroy();
        databaseAdapter.close();
        finish();
    }

    private void populateActivityFields() {
        Log.d(CLASS_NAME, "\t: populateActivityFields : with values as entered by user ...");

        etUser = (EditText) findViewById(R.id.edittextUserLoginName);
        etUserPassword = (EditText) findViewById(R.id.edittextUserLoginPassword);

        btnSignInSubmit = (Button) findViewById(R.id.buttonSignInSubmit);
        btnSignInSubmit.setEnabled(false);

        if (validateAnyEditFieldUsed(new EditText[]{etUser, etUserPassword})) {
            if (validateActivityFields()) btnSignInSubmit.setEnabled(true);

        }
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

    // VALIDATION methods

    private boolean validateActivityFields() {
        boolean isNotValid = false;

        int maxLength = 40;
        final String REGEX_SIGN_IN = ""; // <TODO> add them!

        String userName = etUser.getText().toString();
        String userPasswordEntered = etUserPassword.getText().toString();
        String userPasswordConfirmed = databaseAdapter.getCustomerPassword(userName);

        if (exceedsMaximumLength(userName.length(), maxLength)) {
            etUser.setError(CustomerError.ERROR8001);
            clearEditField(etUser);
            isNotValid = true;
        } else if (isNullField(userName.length())) {
            etUser.setError(CustomerError.ERROR4001);
            clearEditField(etUser);
            isNotValid = true;
        } else if (isNotValidFormatRegex(userName, REGEX_SIGN_IN)) {
            etUser.setError(CustomerError.ERROR3001);
            clearEditField(etUser);
            isNotValid = true;
        } else {
            etUser.setError(null);
            isNotValid = false;
        }
        if (exceedsMaximumLength(userPasswordEntered.length(), maxLength)) {
            etUserPassword.setError(CustomerError.ERROR8001);
            clearEditField(etUserPassword);
            isNotValid = true;
        } else if (isNullField(userPasswordEntered.length())) {
            etUserPassword.setError(CustomerError.ERROR4001);
            clearEditField(etUserPassword);
            isNotValid = true;
        } else if (isNotValidFormatRegex(userPasswordEntered, REGEX_SIGN_IN)) {
            etUserPassword.setError(CustomerError.ERROR3001);
            clearEditField(etUserPassword);
            isNotValid = true;
        } else if (userPasswordConfirmed.equals("")) {
            etUser.setError(DatabaseError.ERROR1006);
            clearEditField(etUser);
            clearEditField(etUserPassword);
            isNotValid = true;
        } else if (!userPasswordEntered.equals(userPasswordConfirmed)) {
            etUserPassword.setError(DatabaseError.ERROR1007);
            clearEditField(etUserPassword);
            isNotValid = true;
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