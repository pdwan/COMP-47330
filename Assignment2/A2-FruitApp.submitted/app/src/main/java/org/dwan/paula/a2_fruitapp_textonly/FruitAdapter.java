package org.dwan.paula.a2_fruitapp_textonly;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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

 File :  FruitAdapter - override getView to create specific ArrayAdapter for FruitItem
 **/

public class FruitAdapter extends ArrayAdapter<FruitItem> {

    Context context;

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Constructor : <b>FruitAdapter</b>
     * @param context reference to activity in which FruitAdapter will be used (FruitAppMain.java)
     * @param layoutResourceId resource id of layout file in which row of image and text will be
     *                         displayed.
     * @param data array of FruitItem objects, displayed as data by Adapter.
     */
    public FruitAdapter(Context context, int layoutResourceId, List<FruitItem> data) {
        super(context, layoutResourceId, data);
        this.context = context;
     }

    /***
     * <b>getView</b> : return row specific to each FruitItem
     * @param position
     * @param view
     * @param parent
     * @return row of image & text
     */
    @Override
    public View getView (int position, View view, ViewGroup parent) {
        FruitHolder fruitHolder = null;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item, null);
            fruitHolder=new FruitHolder();
            fruitHolder.image_icon = (ImageView)view.findViewById(R.id.image_icon);
            fruitHolder.text_fruit = (TextView)view.findViewById(R.id.text_fruit);
            view.setTag(fruitHolder);
        } else {
            fruitHolder = (FruitHolder)view.getTag();
        }
        FruitItem fruitItem = getItem(position);
        fruitHolder.image_icon.setImageResource(fruitItem.icon);
        fruitHolder.text_fruit.setText(fruitItem.fruit);

        return view;
    }

    private class FruitHolder {
        ImageView image_icon;
        TextView text_fruit;
    }
}
