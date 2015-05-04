package org.dwan.paula.lunarenterprises.store;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    DisplayStoreSpecificInfoActivity.java
            Lists points balancefor selected store as well as vouchers available depending on
            points applicable for that store.
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.dwan.paula.lunarenterprises.GlobalVariableCustomerId;
import org.dwan.paula.lunarenterprises.R;
import org.dwan.paula.lunarenterprises.database.DatabaseAdapter;
import org.dwan.paula.lunarenterprises.monochrome.GenerateZXingCodeActivity;

public class DisplayStoreSpecificInfoActivity extends ActionBarActivity {

    private static final String CLASS_NAME = "DisplayStoreSpecific";
    public static final String STORE_NAME = "storeName";
    public static final String STORE_IMAGE = "storeImage";
    public static final String STORE_VOUCHER = "storeVoucher";

    DatabaseAdapter databaseAdapter;
    ListView listViewVouchers;
    String voucherDescription = "";
    String image = "";
    String store = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "\t: onCreate ...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_store_specific_info);

        Intent storeInfoIntent = getIntent();
        populateStoreIntent(storeInfoIntent);
    }

    private void populateStoreIntent(Intent intent) {
        Log.d(CLASS_NAME, "\t: populateStoreIntent ...");

        int imageId = -1;
        int pointsBalance = 0;

        if (intent != null) {
            store = intent.getStringExtra(DisplayStoreListingActivity.STORE_NAME);
            image = intent.getStringExtra(DisplayStoreListingActivity.STORE_IMAGE);
            imageId = getResources().getIdentifier(image, "drawable", getPackageName());
            pointsBalance = Integer.parseInt(intent.getStringExtra(DisplayStoreListingActivity.STORE_POINTS));
        }

        ImageView tvImage = (ImageView) findViewById(R.id.imageivewImageName);
        tvImage.setImageResource(imageId);
        TextView tvStore = (TextView) findViewById(R.id.textviewStoreName);
        tvStore.setText(store);
        TextView tvPoints = (TextView) findViewById(R.id.textviewPoints);
        tvPoints.setText(pointsBalance);

        String[] vouchersListing = new String[] {"Null Voucher"};
        if (pointsBalance >= 1000)
            vouchersListing[vouchersListing.length] = "20% voucher";
        else if (pointsBalance >= 750) {
            vouchersListing[vouchersListing.length] = "10% voucher";
        } else if (pointsBalance >= 500) {
            vouchersListing[vouchersListing.length] = "€10 voucher";

        } else if (pointsBalance >= 250) {
            vouchersListing[vouchersListing.length] = "€5 voucher";
        }
        ArrayAdapter<String> voucherAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_display_store_specific_info, vouchersListing);
        listViewVouchers = (ListView) findViewById(R.id.listViewVouchers);
        listViewVouchers.setAdapter(voucherAdapter);

        listViewVouchers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvVoucher = (TextView) findViewById(R.id.textviewVoucher);
                voucherDescription = tvVoucher.getText().toString();
            }
        });
    }

    /**
     * begin collecting points for this store - store is not currently in store4customer.tb
     * needs to be added.
     */
    public void startCollectingPoints(){

        int storeId = databaseAdapter.getStoreIdUsingStoreName(store);

        final GlobalVariableCustomerId globalVariableCustomerId = (GlobalVariableCustomerId) getApplicationContext();
        int points = databaseAdapter.getStorePointsBalanceUsingStoreId(storeId,globalVariableCustomerId.getCustId());

        if (points == -1)
            databaseAdapter.createStoreForCustomerPoints(storeId,globalVariableCustomerId.getCustId(), 0);
        else if (points >= 0)
            Toast.makeText(getApplicationContext(), StoreError.ERROR9005, Toast.LENGTH_SHORT).show();
    }

    /**
     * return to calling activity - Store Listing
     *
     * @param view
     */
    public void backToStoreListing(View view) {
        Log.d(CLASS_NAME, "\t: backToStoreListing ...");

        finish();
    }

    /**
     * select voucher from listing depending on points available.
     *
     * @param view
     */
    public void generateZXingCode(View view) {
        Log.d(CLASS_NAME, "\t: generate ZXing code ...");

        if (voucherDescription != "") {
            Intent generateZXingIntent = new Intent(this, GenerateZXingCodeActivity.class);
            generateZXingIntent.putExtra(STORE_IMAGE, image);
            generateZXingIntent.putExtra(STORE_NAME, store);
            generateZXingIntent.putExtra(STORE_VOUCHER, voucherDescription);
            startActivity(generateZXingIntent);
        } else
            Toast.makeText(getApplicationContext(), StoreError.ERROR9003, Toast.LENGTH_LONG).show();
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
                Toast.makeText(this, StoreError.ERROR9001, Toast.LENGTH_SHORT).show();
                break;
            case R.id.overflow_help:
                Toast.makeText(this, StoreError.ERROR9002, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

}