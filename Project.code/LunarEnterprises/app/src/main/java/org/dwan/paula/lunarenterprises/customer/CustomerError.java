package org.dwan.paula.lunarenterprises.customer;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    CustomerError.java
            <b><i>ENUM</i></b> of all error messages in the app - centralised for ease of use, update, etc.
*/

enum CustomerError implements CharSequence {

    ERROR3001("ERR-3001 : Field accepts [0-9] [a-z] [A-Z] [- , . ; :] only."),
    ERROR3002("ERR-3002 : Addresses 1, 2 & 4 must be provided, only address 3 is optional."),
    ERROR3003("ERR-3003 : Mobile valid format is 08<x>-<7 digits>, e.g.: 085-1234567."),
    ERROR3004("ERR-3004 : Email valid format is user.email@domain, e.g.: my.email@gmail.com."),
    ERROR3005("ERR-3005 : Passwords as entered do not match."),

    // No null fields
    ERROR4001("ERR-4001 : Field cannot be null / empty."),

    // if check boxes enabled, the contact info must be provided
    ERROR5001("ERR-5001 : Allow SMS contact option checked - No Mobile number provided."),
    ERROR5002("ERR-5002 : Allow EMail contact option checked - No Email provided."),

    // max lengths exceeded
    ERROR8001("ERR-8001 : Max length for <First name> | <User login> | <Password> is <40> characters."),
    ERROR8002("ERR-8002 : Max length for Family Name is <50> characters."),
    ERROR8003("ERR-8003 : Max length for Address <1|2|3|4> is <50> characters."),
    ERROR8004("ERR-8004 : Max length for Mobile number is <11> characters : '08<x>-1234567' "),
    ERROR8005("ERR-8005 : Max length for Email is <50> characters : e.g.: <user>@domain.ie"),

    ERROR9001("ERR-9001 : For more help on this app, please see : http://www.lunar-stores.ie"),
    ERROR9002("ERR-9002 : De-register is not available at this stage.");

    private final String message;

    private CustomerError(final String message) {
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