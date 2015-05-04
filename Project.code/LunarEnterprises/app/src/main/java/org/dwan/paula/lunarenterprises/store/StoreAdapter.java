package org.dwan.paula.lunarenterprises.store;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    StoreAdapter.java
            Used in population of store listing - override getView to create specific ArrayAdapter
            for StoreItem
*/

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.dwan.paula.lunarenterprises.R;

import java.util.List;

public class StoreAdapter extends ArrayAdapter<StoreItem> {

    private static final String CLASS_NAME = "StoreAdapter";
    Context context;

    /**
     * Constructor : <b>StoreAdapter</b>
     *
     * @param context          reference to activity in which StoreAdapter will be used
     * @param layoutResourceId resource id of layout file in which row of image and text will be
     *                         displayed.
     * @param data             array of StoreItem objects, displayed as data by Adapter.
     */
    public StoreAdapter(Context context, int layoutResourceId, List<StoreItem> data) {
        super(context, layoutResourceId, data);
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * <b>getView</b> : return row specific to each StoreItem
     *
     * @param position
     * @param view
     * @param parent
     * @return row of image and text
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Log.d(CLASS_NAME, "\t: getView return row specific to each StoreItem ...");
        StoreHolder storeHolder = null;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_image_store, null);
            storeHolder = new StoreHolder();
            storeHolder.image_icon = (ImageView) view.findViewById(R.id.imageviewIcon);
            storeHolder.text_store = (TextView) view.findViewById(R.id.textviewStore);
            view.setTag(storeHolder);
        } else {
            storeHolder = (StoreHolder) view.getTag();
        }
        StoreItem storeItem = getItem(position);
        storeHolder.image_icon.setImageResource(storeItem.icon);
        storeHolder.text_store.setText(storeItem.store);

        return view;
    }

    private class StoreHolder {
        ImageView image_icon;
        TextView text_store;
    }
}
