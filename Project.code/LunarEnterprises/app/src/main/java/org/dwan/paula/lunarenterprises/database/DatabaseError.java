package org.dwan.paula.lunarenterprises.database;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    DatabaseError.java
            <b><i>ENUM</i></b> of all database error messages in the app - centralised for ease of use, update, etc.
*/

public enum DatabaseError implements CharSequence {

    ERROR1001("ERR-1001 : Database does not exist."),
    ERROR1002("ERR-1002 : Database entry does not exist for the specified customer."),
    ERROR1003("ERR-1003 : Database or database location is read only."),
    ERROR1004("ERR-1004 : Database is not available at this time."),
    ERROR1005("ERR-1005 : Database is locked by another user."),
    ERROR1006("ERR-1006 : Customer not present in database."),
    ERROR1007("ERR-1007 : Password | user name does not match what is in the database."),

    //  <TODO> Customer login error messages
    ERROR2001("ERR-2001 : Customer login name must be lower case only."),
    ERROR2002("ERR-2002 : Customer login name must be unique, please choose another."),;

    private final String message;

    private DatabaseError(final String message) {
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