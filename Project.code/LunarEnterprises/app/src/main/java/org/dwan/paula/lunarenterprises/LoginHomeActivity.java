package org.dwan.paula.lunarenterprises;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    LoginHomeActivity.java
            This is the first screen the user sees --> home screen.
            Here the customer logs into and existing account or creates a new account.
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
import android.widget.Toast;

import org.dwan.paula.lunarenterprises.customer.RegisterTermsAndConditionsActivity;
import org.dwan.paula.lunarenterprises.customer.SignInActivity;
import org.dwan.paula.lunarenterprises.database.DatabaseAdapter;
import org.dwan.paula.lunarenterprises.database.DatabaseTablesInitialization;

import java.sql.SQLException;

public class LoginHomeActivity extends ActionBarActivity {

    private static final String CLASS_NAME = "LoginHomeActivity";
    DatabaseAdapter databaseAdapter;

    /**
     * onCreate : home screen layout - your choice!
     * <ul>
     * <li>[Sign In] login as existing user,</li>
     * <li>[Register] register as new user</li>
     * </ul>
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "\t: onCreate - login as new or existing user ...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);

        databaseAdapter = new DatabaseAdapter(this);
        try {
            databaseAdapter = databaseAdapter.open();
            DatabaseTablesInitialization databaseTablesInitialization = new DatabaseTablesInitialization();
            //databaseTablesInitialization.populateCustomerTable();
            //databaseTablesInitialization.populateStoreTable();
            // databaseTablesInitialization.populateStoreForCustomerTable();
        } catch (SQLException e) {
            Log.d(CLASS_NAME, "\t: onCreate - login as new or existing user - SQL exception ...");
            e.printStackTrace();
        }
    }

    /**
     * register New User : handle Click Event of <b>Register</b> button, call new layout
     * opens T&C's first, then requests customer's details.
     *
     * @param view
     */
    public void registerNewUser(View view) {
        Log.d(CLASS_NAME, "\t: register as new user, call new layout ...");

        Intent intentRegister = new Intent(getApplicationContext(), RegisterTermsAndConditionsActivity.class);
        startActivity(intentRegister);
    }

    /**
     * Sign-in as Existing User : handle Click Event of <b>Sign In</b> button, call new layout and
     * get login information.
     *
     * @param view
     */
    public void signInExistingUser(View view) {
        Log.d(CLASS_NAME, "\t: sign in as existing user, call new layout ...");

        Intent intentSignIn = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(intentSignIn);
    }

    /**
     * onDestroy - home screen, close database
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

        final String HELP_INFO = "For more help on this app, please see : http://www.lunar-stores.ie";
        final String DEREGISTER_INFO = "De-register is not available at this stage.";

        switch (item.getItemId()) {
            case R.id.overflow_close:
                onDestroy();
                break;
            case R.id.overflow_deregister:
                // DatabaseAdapter databaseAdapter ;
                // DeRegisterCustomer deRegisterCustomer = new DeRegisterCustomer();
                // DeRegisterCustomer.removeCustomerFromTable(userName);
                Toast.makeText(this, DEREGISTER_INFO, Toast.LENGTH_SHORT).show();
                break;
            case R.id.overflow_help:
                Toast.makeText(this, HELP_INFO, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}