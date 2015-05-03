package org.dwan.paula.a2_fruitapp_textonly;

/***
    Student     : Paula Dwan
    Student ID  : 13208660
    Course      : COMP-47330 Practical Android Computing
    Assignment  : 2
    Due date    : 23-March-2015

 Summary : Fruit App for Children where
    1a. User launches application - main activity opens - 'FruitAppMain'
    1b. User sees list of fruit (ListView).
    2a. User clicks on one fruit in ListView and opens second activity -
        'DisplayFruitInformation'.
    2b. 'DisplayFruitInformation' fields are populated using arrays from arrays.xml.
        Two languages exist - English (EN) and Swedish (SV)
    3.  In 'DisplayFruitInformation' activity, [Back] button returns user to
        'FruitAppMain'.

 File :  FruitItem - constructor
 **/

public class FruitItem {

    public int icon ;
    public CharSequence fruit;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public CharSequence getFruit() {
        return fruit;
    }

    public void setFruit(CharSequence fruit) {
        this.fruit = fruit;
    }

    public FruitItem () {
        super();
    }

    public FruitItem(int icon, CharSequence fruit) {
        super();
        this.icon=icon;
        this.fruit=fruit;
    }

}