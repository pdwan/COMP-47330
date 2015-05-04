package org.dwan.paula.lunarenterprises.store;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    CustomerError.java
            <b><i>ENUM</i></b> of all error messages in the app - centralised for ease of use, update, etc.
*/

enum StoreError implements CharSequence {

    ERROR9001("ERR-9001 : For more help on this app, please see : http://www.lunar-stores.ie"),
    ERROR9002("ERR-9002 : De-register is not available at this stage."),
    ERROR9003("ERR-9003 : Insufficient points collected for this store."),
    ERROR9004("ERR-9004 : Points not collected for this store, click <Add Store> in next activity to add."),
    ERROR9005("ERR-9004 : Points already collected for this store."),
    ;

    private final String message;

    private StoreError(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public String toString() {
        return message;
    }

}