package org.dwan.paula.lunarenterprises.database;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    DataBaseHelper.java
            Database operation methods for the three tables customer,tb, store.tb and shop4customer.tb
*/

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    //Logging
    private static final String CLASS_NAME = "DataBaseHelper";

    // Database version
    private static final int DATABASE_VERSION = 1;
    // Database name
    private static final String DATABASE_NAME = "lunarStores";

    // DATABASES --> shop.tb, customer.tb, shop4customer.tb
    private static final String TABLE_CUSTOMER = "customer";
    private static final String TABLE_STORE = "store";
    private static final String TABLE_SHOP_FOR_CUSTOMER = "shopforcustomer";

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

    // SQL Excerpts
    private static final String sqlCreateTable = "CREATE TABLE ";
    private static final String sqlLeft = "(";
    private static final String sqlPK = " INTEGER PRIMARY KEY, ";
    private static final String sqlTextNotNull = " TEXT NOT NULL, ";
    private static final String sqlText = " TEXT, ";
    private static final String sqlInteger = " INTEGER, ";
    private static final String sqlDatetime = " DATETIME";
    private static final String sqlRight = ")";

    // TABLE --> create STORE
    private static final String CREATE_TABLE_STORE = sqlCreateTable + TABLE_STORE + sqlLeft
            + KEY_ID + sqlPK + KEY_NAME + sqlTextNotNull + KEY_LOGO + sqlTextNotNull
            + KEY_CONTACT_NAME + sqlTextNotNull + KEY_CONTACT_EMAIL + sqlTextNotNull
            + KEY_CONTACT_PHONE + sqlTextNotNull + KEY_CREATED_AT + sqlDatetime + sqlRight;
    // TABLE --> create CUSTOMER
    private static final String CREATE_TABLE_CUSTOMER = sqlCreateTable + TABLE_CUSTOMER + sqlLeft
            + KEY_ID + sqlPK + KEY_NAME_1 + sqlTextNotNull + KEY_NAME_2 + sqlTextNotNull
            + KEY_ADDRESS_1 + sqlTextNotNull + KEY_ADDRESS_2 + sqlTextNotNull + KEY_ADDRESS_3 + sqlText
            + KEY_ADDRESS_4 + sqlTextNotNull + KEY_EMAIL + sqlText + KEY_MOBILE + sqlText
            + KEY_EMAIL_OK + sqlText + KEY_MOBILE_OK + sqlText + KEY_THIRD_PARTY_OK + sqlText
            + KEY_LOGIN + sqlTextNotNull + KEY_PASSWORD + sqlTextNotNull + KEY_CREATED_AT + sqlDatetime
            + sqlRight;
    // TABLE --> create SHOP-FOR-CUSTOMER
    private static final String CREATE_TABLE_SHOP_FOR_CUSTOMER = sqlCreateTable
            + TABLE_SHOP_FOR_CUSTOMER + sqlLeft + KEY_ID + sqlPK + KEY_STORE_ID + sqlInteger
            + KEY_CUSTOMER_ID + sqlInteger + KEY_POINTS_BALANCE + sqlInteger + sqlRight;

    /**
     * Database initialization
     *
     * @param context
     * @param databaseName
     * @param o
     * @param databaseVersion
     */
    public DataBaseHelper(Context context, String databaseName, Object o, int databaseVersion) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create tables - store,tb,customer.tb, and shop4customer.tb
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(CLASS_NAME, ":\t onCreate - create three tables in SQLite database ...");

        db.execSQL(CREATE_TABLE_STORE);
        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_SHOP_FOR_CUSTOMER);
    }

    /**
     * Upgrade the database - if goes from version 1 to 2, etc.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(CLASS_NAME, ":\t onUpgrade inc. recreate three tables for SQLite ...");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP_FOR_CUSTOMER);
        onCreate(db);
    }
}