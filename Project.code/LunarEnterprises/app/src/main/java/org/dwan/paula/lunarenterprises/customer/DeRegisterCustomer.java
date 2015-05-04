package org.dwan.paula.lunarenterprises.customer;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    DeRegisterCustomer.java
            remove specified customer from database once called by De-Register from ActivityBar
            option menu.
*/

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.dwan.paula.lunarenterprises.database.Customer;
import org.dwan.paula.lunarenterprises.database.DatabaseAdapter;
import org.dwan.paula.lunarenterprises.database.DatabaseError;

public class DeRegisterCustomer {

    private static final String CLASS_NAME = "DeRegisterCustomer";
    DatabaseAdapter databaseAdapter;
    Context context;

    // <INFO> not an activity - need to define constructor for context for Toast to work.
    public DeRegisterCustomer(Context context) {
        this.context = context;
    }

    /**
     * remove customer from customer.tb and also store information from store4customer.tb
     *
     * @param userName - name of use to be deleted
     */
    public void removeCustomerFromTable(String userName) {
        Log.d(CLASS_NAME, "\t: clear all sign-in details as entered by existing customer ...");

        if (databaseAdapter.isUserPresent(userName)) {
            Customer customer = databaseAdapter.getCustomerUsingUserName(userName);
            databaseAdapter.deleteCustomer(customer);
        } else {
            Toast.makeText(context, DatabaseError.ERROR1006.toString(), Toast.LENGTH_LONG).show();
        }
    }
}