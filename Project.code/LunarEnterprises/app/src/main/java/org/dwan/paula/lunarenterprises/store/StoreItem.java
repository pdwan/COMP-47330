package org.dwan.paula.lunarenterprises.store;


/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    StoreItem.java
	       constructor for store listing
*/

public class StoreItem {

    public int icon;
    public CharSequence store;

    public StoreItem() {
        super();
    }

    public StoreItem(int icon, String store) {
        super();
        this.icon = icon;
        this.store = store;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public CharSequence getStore() {
        return store;
    }

    public void setStore(CharSequence store) {
        this.store = store;
    }

}