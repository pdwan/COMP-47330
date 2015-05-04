package org.dwan.paula.lunarenterprises.store;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    DisplayStoreListingActivity.java
            Lists all stores in store.tb in database.
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import org.dwan.paula.lunarenterprises.R;
import org.dwan.paula.lunarenterprises.database.DatabaseAdapter;
import org.dwan.paula.lunarenterprises.database.Store;

import java.util.ArrayList;
import java.util.List;

public class DisplayStoreListingActivity extends ActionBarActivity implements OnItemClickListener {

    public static final String STORE_NAME = "storeName";
    public static final String STORE_IMAGE = "storeImage";
    public static final String STORE_POINTS = "storePoints";
    private static final String CLASS_NAME = "DisplayStoreListing";
    DatabaseAdapter databaseAdapter;
    List<Store> stores = new ArrayList<Store>();
    ListView storeListView;
    List<StoreItem> storeItems;
    String[] storeNames = null;
    int[] storeImages = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "\t: onCreate ...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_store_listing);

        populateListViewStores();
    }


    private void populateListViewStores() {
        Log.d(CLASS_NAME, "\t: populateListViewStores for all stores ...");

        int i = 0;
        for (Store store : stores) {
            storeImages[i] = Integer.parseInt("R.drawable" + store.getLogo());
            storeNames[i] = store.getName().toString();
            i++;
        }

        storeItems = new ArrayList<StoreItem>();

        for (int x = 0; x < storeImages.length; x++) {
            StoreItem item = new StoreItem(storeImages[x], storeNames[x]);
            storeItems.add(item);
        }

        storeListView = (ListView) findViewById(R.id.storeList);
        storeListView.setAdapter(new StoreAdapter(this, R.layout.list_item_image_store, storeItems));
        storeListView.setOnItemClickListener(this);
    }

    /**
     * onClick listener for row selected in ListView of all Stores.
     *
     * @param parent
     * @param view
     * @param position
     * @param _id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long _id) {
        Log.d(CLASS_NAME, "\t: onItemClick for selected row ...");

        int imageId = storeImages[position];
        String store = storeNames[position];

        int storeId = databaseAdapter.getStoreIdUsingStoreName(store);
        int points = databaseAdapter.getStorePointsBalanceUsingStoreId(storeId);

        Intent intentStoreListing = new Intent(this, DisplayStoreSpecificInfoActivity.class);
        intentStoreListing.putExtra(STORE_IMAGE, imageId);
        intentStoreListing.putExtra(STORE_NAME, store);
        intentStoreListing.putExtra(STORE_POINTS, points);
        startActivity(intentStoreListing);
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
