package org.dwan.paula.lunarenterprises.database;

import android.util.Log;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    DatabaseTablesInitialization.java
            Populate tables with initial values.
*/
public class DatabaseTablesInitialization {

    private static final String CLASS_NAME = "DatabaseTablesInitialization";
    DatabaseAdapter databaseAdapter;

    /**
     * add customers John Ryan, Amy Collins, Emily Blackwell, Max Roberts, and Cathy Jones
     * to customer.tb
     */
    public void populateCustomerTable() {
        Log.d(CLASS_NAME, "\t:populateCustomerTable");

        Customer customer = new Customer();
        customer.setName1("John");                  // customer1
        customer.setName2("Ryan");
        customer.setAddress1("12 Main St.,");
        customer.setAddress2("Douglas");
        customer.setAddress3("");
        customer.setAddress4("Cork");
        customer.setEmail("jryan@gmail.com");
        customer.setMobile("087-7766321");
        customer.setEmailOk("1");
        customer.setMobileOk("1");
        customer.setThirdPartyOk("1");
        customer.setNameLogin("jryan");
        customer.setPasswordLogin("password");
        databaseAdapter.createCustomer(customer);
        customer = null;                            // customer 2
        customer.setName1("Amy");
        customer.setName2("Collins");
        customer.setAddress1("123 Rock Ave,");
        customer.setAddress2("Cork");
        customer.setAddress3("");
        customer.setAddress4("Cork");
        customer.setEmail("acollins@gmail.com");
        customer.setMobile("085-7654321");
        customer.setEmailOk("1");
        customer.setMobileOk("0");
        customer.setThirdPartyOk("0");
        customer.setNameLogin("acollins");
        customer.setPasswordLogin("password");
        databaseAdapter.createCustomer(customer);
        customer = null;                            // customer 3
        customer.setName1("Emily");
        customer.setName2("Blackwell");
        customer.setAddress1("555 Roswell Ave.");
        customer.setAddress2("Blessington");
        customer.setAddress3("");
        customer.setAddress4("Wicklow");
        customer.setEmail("emily@gmail.com");
        customer.setMobile("085-7654000");
        customer.setEmailOk("0");
        customer.setMobileOk("1");
        customer.setThirdPartyOk("0");
        customer.setNameLogin("eblackwell");
        customer.setPasswordLogin("password");
        databaseAdapter.createCustomer(customer);
        customer = null;                            // customer 4
        customer.setName1("Max");
        customer.setName2("Roberts");
        customer.setAddress1("25 Second St.,");
        customer.setAddress2("Naas Rd.,");
        customer.setAddress3("Blessington");
        customer.setAddress4("Wicklow");
        customer.setEmail("mroberts@gmail.com");
        customer.setMobile("086-9988771");
        customer.setEmailOk("0");
        customer.setMobileOk("0");
        customer.setThirdPartyOk("1");
        customer.setNameLogin("robertsm");
        customer.setPasswordLogin("password");
        databaseAdapter.createCustomer(customer);
        customer = null;                            // customer 5
        customer.setName1("Cathy");
        customer.setName2("Jones");
        customer.setAddress1("22 O'Connell St.");
        customer.setAddress2("Dublin 1");
        customer.setAddress3("");
        customer.setAddress4("Dublin 1");
        customer.setEmail("jones123@gmail.com");
        customer.setMobile("");
        customer.setEmailOk("0");
        customer.setMobileOk("0");
        customer.setThirdPartyOk("0");
        customer.setNameLogin("jonesc");
        customer.setPasswordLogin("password");
        databaseAdapter.createCustomer(customer);
    }

    /**
     * add stores Alpha Stores, Beta Stores, Orange Stores, $$$-Stores, and €€€-Stores
     * <p/>
     * to store.tb
     */
    public void populateStoreTable() {
        Log.d(CLASS_NAME, "\t:populateCustomerTable");

        Store store = new Store();
        store.setName("Alpha Stores");                      // store 1
        store.setLogo("logo_alpha");
        store.setContactName("Joe Walshe");
        store.setContactEmail("alphastores@gmail.com");
        store.setContactNumber("085-1122345");
        databaseAdapter.createStore(store);
        store = null;
        store.setName("Beta Stores");                      // store 2
        store.setLogo("logo_beta");
        store.setContactName("Peter Roberts");
        store.setContactEmail("betastores@gmail.com");
        store.setContactNumber("087 -2323543");
        databaseAdapter.createStore(store);
        store = null;
        store.setName("Orange Stores");                      // store 3
        store.setLogo("logo_orange");
        store.setContactName("Emma Williams");
        store.setContactEmail("info@orange-stores.com");
        store.setContactNumber("085-5551234");
        databaseAdapter.createStore(store);
        store = null;
        store.setName("$$$-Stores");                      // store 4
        store.setLogo("logo_dollar");
        store.setContactName("Trixie McRoy");
        store.setContactEmail("info@dollar-stores.com");
        store.setContactNumber("086-1234555");
        databaseAdapter.createStore(store);
        store = null;
        store.setName("€€€-Stores");                      // store 5
        store.setLogo("logo_euro");
        store.setContactName("Josh Williamson");
        store.setContactEmail("info@euro-stores.com");
        store.setContactNumber("085-1221343");
        databaseAdapter.createStore(store);
    }

    /**
     * add customer and store relationship to store4customer.tb
     * what store customers John Ryan, Amy Collins, Emily Blackwell, Max Roberts, and Cathy Jones
     * have signed up for?
     */
    public void populateStoreForCustomerTable() {
        Log.d(CLASS_NAME, "\t:populateStore4CustomerTable");

        databaseAdapter.createStoreForCustomerPoints(1, 1, 100);
        databaseAdapter.createStoreForCustomerPoints(2, 1, 200);
        databaseAdapter.createStoreForCustomerPoints(3, 2, 300);
        databaseAdapter.createStoreForCustomerPoints(4, 2, 400);
        databaseAdapter.createStoreForCustomerPoints(5, 3, 500);
        databaseAdapter.createStoreForCustomerPoints(1, 3, 600);
        databaseAdapter.createStoreForCustomerPoints(2, 4, 700);
        databaseAdapter.createStoreForCustomerPoints(3, 4, 800);
        databaseAdapter.createStoreForCustomerPoints(4, 5, 900);
        databaseAdapter.createStoreForCustomerPoints(5, 5, 1000);
    }
}