package org.dwan.paula.lunarenterprises.customer;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.dwan.paula.lunarenterprises.R;
import org.dwan.paula.lunarenterprises.database.DatabaseAdapter;


public class UpdateExistingCustomerActivity extends ActionBarActivity {

    private static final String CLASS_NAME = "UpdateExistingCustomer";
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "\t: onCreate ...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_existing_customer);
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
