package org.dwan.paula.lunarenterprises.database;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    StoreForCustomer.java
            Equivalent to Store4Customer.tb
*/

public class StoreForCustomer {

    int id, customerId, StoreId, storePoints;

    // constructors

    public StoreForCustomer() {
    }

    public StoreForCustomer(int id, int customerId, int storeId, int storePoints) {
        this.id = id;
        this.customerId = customerId;
        this.StoreId = storeId;
        this.storePoints = storePoints;
    }

    public StoreForCustomer(int customerId, int storeId, int storePoints) {
        this.customerId = customerId;
        this.StoreId = storeId;
        this.storePoints = storePoints;
    }

    //  getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // setters

    public int getStoreId() {
        return StoreId;
    }

    public void setStoreId(int storeId) {
        this.StoreId = storeId;
    }

    public int getStorePoints() {
        return storePoints;
    }

    public void setStorePoints(int storePoints) {
        this.storePoints = storePoints;
    }

}