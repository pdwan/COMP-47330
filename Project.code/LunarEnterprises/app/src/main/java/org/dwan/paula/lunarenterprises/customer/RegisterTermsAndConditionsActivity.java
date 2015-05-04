package org.dwan.paula.lunarenterprises.customer;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    RegisterTermsAndConditionsActivity.java
            New user agrees to T&Cs (and confirms has read and understood them) and
            may then register contact details, personal preferences regarding contact methods,
            and login details.
            If new user does not agree to T&Cs then app closes.
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.dwan.paula.lunarenterprises.R;

public class RegisterTermsAndConditionsActivity extends ActionBarActivity {

    private static final String CLASS_NAME = "RegisterTAndCsActivity";
    private Button btnAgreeYes;
    private CheckBox chkReadTCs;

    /**
     * onCreate : Register as new user - Confirm T&Cs are read and understood and then user's choice.
     * <ul>
     * <li>[Agree] : Agree to T&C's, may now provide details.</li>
     * <li>[Don't] : Do not agree to T&C's - app closes.</li>
     * </ul>
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "\t: onCreate - agree or not ..");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_terms_and_conditions);

        btnAgreeYes = (Button) findViewById(R.id.buttonAgreeYes);
        chkReadTCs = (CheckBox) findViewById(R.id.checkBoxTandCRead);
        chkReadTCs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnAgreeYes.setEnabled(true);

                } else {
                    btnAgreeYes.setEnabled(false);
                }
            }
        });
    }

    /**
     * <b>Agree</b> to T&C's : handle Click Event of <b>Agree</b> button, call new layout and
     * get login information including duplicate password for validation.
     *
     * @param view
     */
    public void AgreeToTermsAndConditions(View view) {
        Log.d(CLASS_NAME, "\t: Agree to T&C's, call new layout ...");

        Intent intentRegisterDetails = new Intent(getApplicationContext(), RegisterCustomerDetailsActivity.class);
        startActivity(intentRegisterDetails);
    }

    /**
     * Do <b>Not Agree</b> to T&C's : handle Click Event of <b>Don't</b> button, close database and app.
     *
     * @param view
     */
    public void DoNotAgreeToTermsAndConditions(View view) {
        Log.d(CLASS_NAME, "\t: Do Not Agree to T&C's, finish ...");

        // LoginHomeActivity.onDestroy();
        finish();
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
}