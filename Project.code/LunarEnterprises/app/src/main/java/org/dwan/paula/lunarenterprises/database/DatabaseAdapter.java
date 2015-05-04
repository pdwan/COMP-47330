package org.dwan.paula.lunarenterprises.database;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    DatabaseAdapter.java
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseAdapter extends ActionBarActivity {

    private static final String CLASS_NAME = "DatabaseAdapter";

    // Database version
    private static final int DATABASE_VERSION = 1;
    // Database name
    private static final String DATABASE_NAME = "lunarStores";

    // DATABASES --> shop.tb, customer.tb, shop4customer.tb
    private static final String TABLE_CUSTOMER = "customer";
    private static final String TABLE_STORE = "store";
    private static final String TABLE_STORE_FOR_CUSTOMER = "shopforcustomer";

    // COMMON column --> keys
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "createdAt";
    // STORE table --> keys
    private static final String KEY_NAME = "store_name";
    private static final String KEY_LOGO = "store_logo";
    private static final String KEY_CONTACT_NAME = "store_contact_name";
    private static final String KEY_CONTACT_EMAIL = "store_contact_email";
    private static final String KEY_CONTACT_PHONE = "store_contact_phone";
    // CUSTOMER table --> keys
    private static final String KEY_NAME_1 = "customer_name1";
    private static final String KEY_NAME_2 = "customer_name2";
    private static final String KEY_ADDRESS_1 = "address1";
    private static final String KEY_ADDRESS_2 = "address2";
    private static final String KEY_ADDRESS_3 = "address3";
    private static final String KEY_ADDRESS_4 = "address4";
    private static final String KEY_EMAIL = "customer_email";
    private static final String KEY_MOBILE = "customer_mobile";
    private static final String KEY_EMAIL_OK = "customer_email_ok";
    private static final String KEY_MOBILE_OK = "customer_mobile_ok";
    private static final String KEY_THIRD_PARTY_OK = "customer_3party_ok";
    private static final String KEY_LOGIN = "customer_login";
    private static final String KEY_PASSWORD = "customer_password";
    // SHOP-FOR-CUSTOMER table --> keys
    private static final String KEY_CUSTOMER_ID = "customer_id";
    private static final String KEY_STORE_ID = "store_id";
    private static final String KEY_POINTS_BALANCE = "points_balance";

    private final Context context;
    public SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    /**
     * DatabaseAdapter for LunarStores SQLite database
     *
     * @param _context
     */
    public DatabaseAdapter(Context _context) {
        Log.d(CLASS_NAME, "\t: DatabaseAdapter ...");

        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * open - SQLite LunarStores database
     *
     * @return SQLite database
     * @throws SQLException
     */
    public DatabaseAdapter open() throws SQLException {
        Log.d(CLASS_NAME, "\t: open SQLite database ...");

        db = dbHelper.getWritableDatabase();

        return this;
    }


    /**
     * get instance of SQLite LunarStores database
     *
     * @return instance of SQLite database
     */
    public SQLiteDatabase getDatabaseInstance() {
        Log.d(CLASS_NAME, "\t: get SQLite database instance ...");

        return db;
    }

    /**
     * close instance of SQLite LunarStores database, if exists and is open
     */
    public void close() {
        Log.d(CLASS_NAME, ":\t close database ...");

        db = dbHelper.getWritableDatabase();
        if (db != null && db.isOpen()) db.close();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }

    // ************************ //
    // TABLE = STORE           //
    // ************************ //

    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>create</i>
     *
     * @param store model
     * @return store_id of store inserted into STORE.tb
     */
    public long createStore(Store store) {
        Log.d(CLASS_NAME, ":\t createTableCustomer using SQLite ...");

        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, store.getName());
        values.put(KEY_LOGO, store.getLogo());
        values.put(KEY_CONTACT_NAME, store.getContactName());
        values.put(KEY_CONTACT_EMAIL, store.getContactEmail());
        values.put(KEY_CONTACT_PHONE, store.getContactNumber());
        values.put(KEY_CREATED_AT, getDateTime());

        long store_id = db.insert(TABLE_STORE, null, values);

        return store_id;
    }

    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>read</i>
     * get store using specific store id
     *
     * @param store_id : id of store whose details are needed
     * @return store matching store_id
     */
    public Store getStore(long store_id) {
        db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_STORE + " WHERE " + KEY_ID + " = " + store_id;

        Log.d(CLASS_NAME, ":\t get Store using query = " + sqlQuery);

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) cursor.moveToFirst();

        Store store = new Store();
        store.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        store.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        store.setLogo(cursor.getString(cursor.getColumnIndex(KEY_LOGO)));
        store.setContactName(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_NAME)));
        store.setContactEmail(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_EMAIL)));
        store.setContactNumber(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_PHONE)));
        store.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
        cursor.close();

        return store;
    }

    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>read</i>
     * get a listing of all stores currently in the database
     *
     * @return List\<stores\> : all stores current available in store.tb
     */
    public List<Store> getAllStores() {

        List<Store> stores = new ArrayList<Store>();
        String sqlQuery = "SELECT * FROM " + TABLE_STORE;

        Log.d(CLASS_NAME, ":\t get All Stores using query = " + sqlQuery);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Store store = new Store();
                store.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                store.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                store.setLogo(cursor.getString(cursor.getColumnIndex(KEY_LOGO)));
                store.setContactName(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_NAME)));
                store.setContactEmail(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_EMAIL)));
                store.setContactNumber(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_PHONE)));
                store.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return stores;
    }

    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>read</i>
     * get a listing of all store names and logos from stores
     *
     * @return List\<stores\> : all names and logos of all stores current available in store.tb
     */
    public List<Store> getAllStoresNamesAndLogos() {

        List<Store> stores = new ArrayList<Store>();
        String sqlQuery = "SELECT  store_name, store_logo FROM " + TABLE_STORE;

        Log.d(CLASS_NAME, ":\t get Stores using query = " + sqlQuery);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Store store = new Store();
                store.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                store.setLogo(cursor.getString(cursor.getColumnIndex(KEY_LOGO)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return stores;
    }

    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>read</i>
     * provides a listing of all stores in use by specified customer
     *
     * @param customer_login : name uses to sign-in to app
     * @return list\<stores\> used by specific customer
     */
    public List<Store> getAllStoresByCustomer(String customer_login) {

        List<Store> stores = new ArrayList<Store>();
        String sqlQuery = "SELECT * FROM " + TABLE_STORE + "td, " + TABLE_CUSTOMER +
                "tg, " + TABLE_STORE_FOR_CUSTOMER + "tt where tg. " + KEY_LOGIN + " = '" +
                customer_login + "'" + " AND tg." + KEY_ID + " = " + "tt." + KEY_CUSTOMER_ID
                + " AND td." + KEY_ID + " = " + "tt." + KEY_STORE_ID;

        Log.d(CLASS_NAME, ":\t get All Stores using query = " + sqlQuery);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Store store = new Store();
                store.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                store.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                store.setLogo(cursor.getString(cursor.getColumnIndex(KEY_LOGO)));
                store.setContactName(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_NAME)));
                store.setContactEmail(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_EMAIL)));
                store.setContactNumber(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_PHONE)));
                store.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
                stores.add(store);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return stores;
    }


    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>read</i>
     * get store id using specified store name
     *
     * @param name of store as string
     * @return store id as int
     */
    public int getStoreIdUsingStoreName(String name) {

        int storeId = 0;

        String sqlQuery = "SELECT * FROM " + TABLE_STORE + " WHERE " + KEY_LOGIN + " = " + name;
        Log.d(CLASS_NAME, ":\t get store for specific store name : " + name + " using query = " + sqlQuery);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            storeId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
        }
        cursor.close();

        return storeId;
    }

    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>read</i>
     * Count all stores in table
     *
     * @return count - number of stores in stores.tb
     */
    public int getStoreCount() {

        String sqlQuery = "SELECT * FROM " + TABLE_STORE;
        Log.d(CLASS_NAME, ":\t count All Stores using query = " + sqlQuery);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>update</i>
     * Updates a single store in the stored.tb
     *
     * @param store store to be updated
     * @return updated row in store.tb for specified store
     */
    public int updateStore(Store store) {

        Log.d(CLASS_NAME, ":\t update store details ...");
        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, store.getName());
        contentValues.put(KEY_LOGO, store.getLogo());
        contentValues.put(KEY_CONTACT_NAME, store.getContactName());
        contentValues.put(KEY_CONTACT_EMAIL, store.getContactEmail());
        contentValues.put(KEY_CONTACT_PHONE, store.getContactNumber());

        return db.update(TABLE_STORE, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(store.getId())});
    }

    /**
     * TABLE_STORE - <b>CRUD</b> methods --> <i>delete</i>
     * delete single store as specified, if it exists
     *
     * @param store_id : : id of store which is selected for deletion
     */
    public void deleteStore(long store_id) {
        Log.d(CLASS_NAME, ":\t delete store for specified store id ...");

        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_STORE, KEY_ID + " = ?", new String[]{String.valueOf(store_id)});
    }

    // ************************ //
    // TABLE = CUSTOMER         //
    // ************************ //


    /**
     * TABLE_CUSTOMER - <b>CRUD</b> methods --> <i>create</i>
     * create single customer in customer.tb
     *
     * @param customer model
     * @return customer_id : id of newly created customer
     */
    public long createCustomer(Customer customer) {
        Log.d(CLASS_NAME, ":\t createTableCustomer using SQLite ...");

        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME_1, customer.getName1());
        contentValues.put(KEY_NAME_2, customer.getName2());
        contentValues.put(KEY_ADDRESS_1, customer.getAddress1());
        contentValues.put(KEY_ADDRESS_2, customer.getAddress2());
        contentValues.put(KEY_ADDRESS_3, customer.getAddress3());
        contentValues.put(KEY_ADDRESS_4, customer.getAddress4());
        contentValues.put(KEY_EMAIL, customer.getEmail());
        contentValues.put(KEY_MOBILE, customer.getMobile());
        contentValues.put(KEY_CREATED_AT, getDateTime());
        contentValues.put(KEY_EMAIL_OK, customer.getEmailOk());
        contentValues.put(KEY_MOBILE_OK, customer.getMobileOk());
        contentValues.put(KEY_THIRD_PARTY_OK, customer.getThirdPartyOk());
        contentValues.put(KEY_LOGIN, customer.getNameLogin());
        contentValues.put(KEY_PASSWORD, customer.getPasswordLogin());

        long customer_id = db.insert(TABLE_CUSTOMER, null, contentValues);

        return customer_id;
    }

    /**
     * TABLE_CUSTOMER - <b>CRUD</b> methods --> <i>read</i>
     * retrieve details on specified customer from customer.tb
     *
     * @param userName : unique use signin anme for whom details are needed
     * @return customer model
     */
    public Customer getCustomerUsingUserName(String userName) {

        db = dbHelper.getReadableDatabase();

        String sqlQuery = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + KEY_LOGIN + " = " + userName;
        Log.d(CLASS_NAME, ":\t get single Customer using query = " + sqlQuery);

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) cursor.moveToFirst();

        Customer customer = new Customer();
        customer.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        customer.setName1(cursor.getString(cursor.getColumnIndex(KEY_NAME_1)));
        customer.setName2(cursor.getString(cursor.getColumnIndex(KEY_NAME_2)));
        customer.setAddress1(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_1)));
        customer.setAddress2(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_2)));
        customer.setAddress3(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_3)));
        customer.setAddress4(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_4)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
        customer.setMobile(cursor.getString(cursor.getColumnIndex(KEY_MOBILE)));
        customer.setNameLogin(cursor.getString(cursor.getColumnIndex(KEY_LOGIN)));
        customer.setPasswordLogin(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
        customer.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
        customer.setEmailOk(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_OK)));
        customer.setMobileOk(cursor.getString(cursor.getColumnIndex(KEY_MOBILE_OK)));
        customer.setThirdPartyOk(cursor.getString(cursor.getColumnIndex(KEY_THIRD_PARTY_OK)));

        cursor.close();

        return customer;
    }

    /**
     * TABLE_CUSTOMER - <b>CRUD</b> methods --> <i>read</i>
     * retrieve details on specified customer from customer.tb
     *
     * @param customer_id : id of customer for whom details are needed
     * @return customer model
     */
    public Customer getCustomer(long customer_id) {

        db = dbHelper.getReadableDatabase();

        String sqlQuery = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + KEY_ID + " = " + customer_id;
        Log.d(CLASS_NAME, ":\t get single Customer using query = " + sqlQuery);

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) cursor.moveToFirst();

        Customer customer = new Customer();
        customer.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        customer.setName1(cursor.getString(cursor.getColumnIndex(KEY_NAME_1)));
        customer.setName2(cursor.getString(cursor.getColumnIndex(KEY_NAME_2)));
        customer.setAddress1(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_1)));
        customer.setAddress2(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_2)));
        customer.setAddress3(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_3)));
        customer.setAddress4(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_4)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
        customer.setMobile(cursor.getString(cursor.getColumnIndex(KEY_MOBILE)));
        customer.setNameLogin(cursor.getString(cursor.getColumnIndex(KEY_LOGIN)));
        customer.setPasswordLogin(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
        customer.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
        customer.setEmailOk(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_OK)));
        customer.setMobileOk(cursor.getString(cursor.getColumnIndex(KEY_MOBILE_OK)));
        customer.setThirdPartyOk(cursor.getString(cursor.getColumnIndex(KEY_THIRD_PARTY_OK)));

        cursor.close();

        return customer;
    }

    /**
     * TABLE_CUSTOMER - <b>CRUD</b> methods --> <i>read</i>
     * retrieve all customers in the customer.tb
     *
     * @return List\<customers\> : all customers in customer.tb
     */
    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<Customer>();

        String sqlQuery = "SELECT * FROM " + TABLE_CUSTOMER;
        Log.d(CLASS_NAME, ":\t get single Customer using query = " + sqlQuery);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                customer.setName1(cursor.getString(cursor.getColumnIndex(KEY_NAME_1)));
                customer.setName2(cursor.getString(cursor.getColumnIndex(KEY_NAME_2)));
                customer.setAddress1(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_1)));
                customer.setAddress2(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_2)));
                customer.setAddress3(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_3)));
                customer.setAddress4(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_4)));
                customer.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                customer.setMobile(cursor.getString(cursor.getColumnIndex(KEY_MOBILE)));
                customer.setNameLogin(cursor.getString(cursor.getColumnIndex(KEY_LOGIN)));
                customer.setPasswordLogin(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                customer.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
                customer.setEmailOk(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_OK)));
                customer.setMobileOk(cursor.getString(cursor.getColumnIndex(KEY_MOBILE_OK)));
                customer.setThirdPartyOk(cursor.getString(cursor.getColumnIndex(KEY_THIRD_PARTY_OK)));
                getAllCustomers().add(customer);
            } while (cursor.moveToNext());
        }

        return customers;
    }

    /**
     * TABLE_CUSTOMER - <b>CRUD</b> methods --> <i>read</i>
     * get password for user login name provided
     *
     * @param userName : name of customer
     * @return associated password
     */
    public String getCustomerPassword(String userName) {

        String pw = "";
        String sqlQuery = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + KEY_LOGIN + " = " + userName;
        Log.d(CLASS_NAME, ":\t get password for specific user : " + userName + " using query = " + sqlQuery);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            pw = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
        }
        cursor.close();

        return pw;
    }

    /**
     * TABLE_CUSTOMER - <b>CRUD</b> methods --> <i>read</i>
     * get password for user login name provided
     *
     * @param userName : name of customer
     * @return associated password
     */
    public boolean isUserPresent(String userName) {
        Log.d(CLASS_NAME, ":\t does user exist in database = " + userName);

        boolean doesUserExist = false;
        String sqlQuery = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " + KEY_LOGIN + " = " + userName;
        Log.d(CLASS_NAME, ":\t does user : " + userName + "exist in table using query = " + sqlQuery);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            doesUserExist = (cursor.getString(cursor.getColumnIndex(KEY_NAME)).equals(userName)) ? true : false;
        }
        cursor.close();

        return doesUserExist;
    }

    /**
     * TABLE_CUSTOMER - <b>CRUD</b> methods --> <i>update</i>
     * update some/all details of specified customer in the customer.tb
     *
     * @param customer model
     * @return updated customer
     */
    public int updateCustomer(Customer customer) {
        Log.d(CLASS_NAME, ":\t update specified customer in customer.tb ...");
        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME_1, customer.getName1());
        contentValues.put(KEY_NAME_2, customer.getName2());
        contentValues.put(KEY_ADDRESS_1, customer.getAddress1());
        contentValues.put(KEY_ADDRESS_2, customer.getAddress2());
        contentValues.put(KEY_ADDRESS_3, customer.getAddress3());
        contentValues.put(KEY_ADDRESS_4, customer.getAddress4());
        contentValues.put(KEY_EMAIL, customer.getEmail());
        contentValues.put(KEY_MOBILE, customer.getMobile());
        contentValues.put(KEY_LOGIN, customer.getNameLogin());
        contentValues.put(KEY_PASSWORD, customer.getPasswordLogin());
        contentValues.put(KEY_EMAIL_OK, customer.getEmailOk());
        contentValues.put(KEY_MOBILE_OK, customer.getMobileOk());
        contentValues.put(KEY_THIRD_PARTY_OK, customer.getThirdPartyOk());

        return db.update(TABLE_CUSTOMER, contentValues, KEY_ID + "= ? ", new String[]{String.valueOf(customer.getId())});
    }

    /**
     * TABLE_CUSTOMER - <b>CRUD</b> methods --> <i>delete</i>
     * delete specified customer and associated stores
     *
     * @param customer model
     */
    public void deleteCustomer(Customer customer) {
        Log.d(CLASS_NAME, ":\t delete specified customer in customer.tb ...");
        db = dbHelper.getWritableDatabase();

        // delete Stores associated with Customer
        List<Store> allCustomerStores = getAllStoresByCustomer(customer.getNameLogin());
        for (Store store : allCustomerStores) {
            deleteStore(store.getId());
        }
        // delete customer
        db.delete(TABLE_CUSTOMER, KEY_ID + "= ?", new String[]{String.valueOf(customer.getId())});
    }

    // ************************ //
    // TABLE = STORE-4-CUSTOMER  //
    // ************************ //

    /**
     * TABLE_STORE_FOR_CUSTOMER - <b>CRUD</b> methods --> <i>create</i>
     * create a row in shop4customer.tb containing key id (PK) and store_id (FK from store.tb) and
     * customer_id (FK from customer.tb) thus joining many stores to each customer as needed.
     *
     * @param store_id    : id (PK) of store
     * @param customer_id : id (PK) of customer
     * @return id of added row to shop4customer.tb
     */
    public long createStoreForCustomerPoints(long store_id, long customer_id, long points) {
        Log.d(CLASS_NAME, "\t: create shop4customer.tb - with specific value for points ...");
        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CUSTOMER_ID, customer_id);
        contentValues.put(KEY_STORE_ID, store_id);
        contentValues.put(KEY_POINTS_BALANCE, points);
        contentValues.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_STORE_FOR_CUSTOMER, null, contentValues);

        return id;
    }

    /**
     * TABLE_STORE_FOR_CUSTOMER - <b>CRUD</b> methods --> <i>create</i>
     * create a row in shop4customer.tb containing key id (PK) and store_id (FK from store.tb) and
     * customer_id (FK from customer.tb) thus joining many stores to each customer as needed.
     *
     * @param store_id    : id (PK) of store
     * @param customer_id : id (PK) of customer
     * @return id of added row to shop4customer.tb
     */
    public long createStoreForCustomer(long store_id, long customer_id) {
        Log.d(CLASS_NAME, "\t: create joined shop4customer.tb  ...");
        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CUSTOMER_ID, customer_id);
        contentValues.put(KEY_STORE_ID, store_id);
        contentValues.put(KEY_POINTS_BALANCE, 0);
        contentValues.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_STORE_FOR_CUSTOMER, null, contentValues);

        return id;
    }

    /**
     * TABLE_STORE_FOR_CUSTOMER - <b>CRUD</b> methods --> <i>read </i>
     * retrieve store id for specific store name
     */
    public int getStorePointsBalanceUsingStoreId(int storeId, int custId) {
        Log.d(CLASS_NAME, "\t: update row using storeId = " + storeId);

        int points = -1;
        String sqlQuery = "SELECT * FROM " + TABLE_STORE_FOR_CUSTOMER + " WHERE " + KEY_STORE_ID + storeId;
        Log.d(CLASS_NAME, ":\t get points for store : " + storeId + " and customer : " + custId);

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            points = (cursor.getInt(cursor.getColumnIndex(KEY_POINTS_BALANCE)));
        }
        cursor.close();

        return points;
    }

    /**
     * TABLE_STORE_FOR_CUSTOMER - <b>CRUD</b> methods --> <i>update</i>
     * update specified customer id (FK) for specified key id (PK)
     *
     * @param id      : PK of shop4customer.tb
     * @param storeId : shop id (FK) to update
     * @return updated row
     */
    public int updateStoreForCustomerForStoreId(long id, long storeId) {
        Log.d(CLASS_NAME, "\t: update row using storeId = " + storeId);

        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_STORE_ID, storeId);

        return db.update(TABLE_STORE_FOR_CUSTOMER, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * TABLE_STORE_FOR_CUSTOMER - <b>CRUD</b> methods --> <i>update</i>
     * update specified customer id (FK) for specified key id (PK)
     *
     * @param id          : PK of shop4customer.tb
     * @param customer_id : : customer id (FK) to update
     * @return updated row
     */
    public int updateStoreForCustomerForCustomerId(long id, long customer_id) {
        Log.d(CLASS_NAME, "\t: update row using customer_id = " + customer_id);

        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CUSTOMER_ID, customer_id);

        return db.update(TABLE_STORE_FOR_CUSTOMER, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * @param id
     */
    public void getPointsFromStoreForCustomerForStoreId(long id) {
        Log.d(CLASS_NAME, "\t: get store id from table shop4customer.tb  ...");

        db = dbHelper.getWritableDatabase();
        db.delete(TABLE_STORE_FOR_CUSTOMER, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
}