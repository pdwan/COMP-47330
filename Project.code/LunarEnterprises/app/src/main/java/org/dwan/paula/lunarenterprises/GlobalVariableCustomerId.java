package org.dwan.paula.lunarenterprises;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    GlobalVariableCustomerId.java
            Global class use to share global variables
*/

import android.app.Application;

public class GlobalVariableCustomerId extends Application {

    private int custId;

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

}
