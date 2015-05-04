package org.dwan.paula.lunarenterprises.database;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    Store.java
            Equivalent to store.tb --> store model
*/

public class Store {

    int id;
    String name, logo, contactName, contactEmail, contactNumber, createdAt;

    // constructors

    public Store() {
    }

    public Store(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    public Store(String name, String logo, String contactName, String contactEmail,
                 String contactNumber, String createdAt) {
        this.name = name;
        this.logo = logo;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
    }

    public Store(int id, String name, String logo, String contactName, String contactEmail,
                 String contactNumber, String createdAt) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
    }

    // getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    // setters

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}