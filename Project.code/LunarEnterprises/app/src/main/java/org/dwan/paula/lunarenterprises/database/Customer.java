package org.dwan.paula.lunarenterprises.database;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    Customer.java
            Equivalent to customer.tb --> customer model
*/

public class Customer {

    int id;
    String name1, name2, address1, address2, address3, address4, email, mobile,
            emailOk, mobileOk, thirdPartyOk, nameLogin, passwordLogin, createdAt;

    // Constructors

    public Customer() {
    }

    public Customer(String name1, String name2, String address1, String address2,
                    String address3, String address4, String email, String mobile,
                    String emailOk, String mobileOk, String thirdPartyOk,
                    String nameLogin, String passwordLogin, String createdAt) {
        this.name1 = name1;
        this.name2 = name2;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.address4 = address4;
        this.email = email;
        this.mobile = mobile;
        this.createdAt = createdAt;
        this.emailOk = emailOk;
        this.mobileOk = mobileOk;
        this.thirdPartyOk = thirdPartyOk;
        this.nameLogin = nameLogin;
        this.passwordLogin = passwordLogin;
    }

    public Customer(int id, String name1, String name2, String address1, String address2,
                    String address3, String address4, String email, String mobile,
                    String emailOk, String mobileOk, String thirdPartyOk,
                    String nameLogin, String passwordLogin, String createdAt) {
        this.id = id;
        this.name1 = name1;
        this.name2 = name2;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.address4 = address4;
        this.email = email;
        this.mobile = mobile;
        this.emailOk = emailOk;
        this.mobileOk = mobileOk;
        this.thirdPartyOk = thirdPartyOk;
        this.nameLogin = nameLogin;
        this.passwordLogin = passwordLogin;
        this.createdAt = createdAt;

    }

    // getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getEmail() {
        return email;
    }

    // Setters

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailOk() {
        return emailOk;
    }

    public void setEmailOk(String emailOk) {
        this.emailOk = emailOk;
    }

    public String getMobileOk() {
        return mobileOk;
    }

    public void setMobileOk(String mobileOk) {
        this.mobileOk = mobileOk;
    }

    public String getThirdPartyOk() {
        return thirdPartyOk;
    }

    public void setThirdPartyOk(String thirdPartyOk) {
        this.thirdPartyOk = thirdPartyOk;
    }

    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String nameLogin) {
        this.nameLogin = nameLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

