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

File :  FruitAppMain - mainActivity
**/

import android.content.res.Configuration;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FruitAppMain extends ActionBarActivity implements OnItemClickListener {

    private static final String CLASS_NAME = "FruitAppMain";

    public static final String FRUIT_NAME = "fruitName";
    public static final String FRUIT_DESC = "fruitDesc";
    public static final String FRUIT_IMAGE = "fruitImage";
    public static final String RECIPE_NAME = "recipeName";
    public static final String RECIPE_STEPS = "recipeSteps";

    ListView fruitListView;
    List<FruitItem> fruitItems;

    /**
     * <b>onCreate</b> : main Activity, produces listing of fruit for which more information
     * is available and may be viewed in secondary activity.
     * Screen orientation is validated for portrait or landscape.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, " :\tonCreate");

        super.onCreate(savedInstanceState);

        // check current orientation and amend if necessary
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();
        Point size = new Point();

        if (orientation == Surface.ROTATION_90 || orientation == Surface.ROTATION_270 ) {
            Log.d(CLASS_NAME, " :\tonCreate = Landscape");
            setContentView(R.layout.activity_ifruit_app_main_land);
        } else {
            Log.d(CLASS_NAME, " :\tonCreate = Portrait");
            setContentView(R.layout.activity_ifruit_app_main);
        }

        // populate arrays with images and fruit names
        String[] fruitNames = getResources().getStringArray(R.array.fruit_name);
        int fLen = fruitNames.length;
        CharSequence[] fruitNamesCS = new CharSequence[fLen];
        for (int i = 0; i < fLen; i++) fruitNamesCS[i] = Html.fromHtml(fruitNames[i]);

        int[] fruitImages = { R.drawable.ic_apple, R.drawable.ic_orange, R.drawable.ic_banana, R.drawable.ic_strawberries };

        fruitItems = new ArrayList<FruitItem>();
        for (int i = 0; i<fLen; i++) {
            FruitItem item = new FruitItem(fruitImages[i], fruitNamesCS[i]);
            fruitItems.add(item);
        }

        // display fruit image and fruit text in ListView
        fruitListView = (ListView) findViewById(R.id.fruitList);
        fruitListView.setAdapter(new FruitAdapter(this, R.layout.list_item, fruitItems));
        fruitListView.setOnItemClickListener(this);
    }

    /**
     * <b>onItemClick</b> : if a row in main activity simple list is selected, then calls
     * secondary activity (startActivity(fruitIntent) which displays more information on the
     * fruit item selected.
     *
     * @param parent : adapterview in use
     * @param view : view in use
     * @param position : position of row selected in simple list, used to get information specific
     *                 to the fruit item.
     * @param id : unique identifier of each item for the row selected
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d(CLASS_NAME, " :\tonItemClick for position " + position);

        Intent intentFruitSpecificInfo = new Intent(this, org.dwan.paula.a2_fruitapp_textonly.FruitAppSpecificInfo.class);
        String[] fruitArray = getResources().getStringArray(R.array.fruit_name);
        String[] descArray = getResources().getStringArray(R.array.fruit_desc);
        String[] imageArray = getResources().getStringArray(R.array.fruit_image);
        String[] recipeArray = getResources().getStringArray(R.array.recipe_name);
        String[] stepsArray = getResources().getStringArray(R.array.recipe_steps);

        intentFruitSpecificInfo.putExtra(FRUIT_NAME, fruitArray[position]);
        intentFruitSpecificInfo.putExtra(FRUIT_DESC, descArray[position]);
        intentFruitSpecificInfo.putExtra(FRUIT_IMAGE, imageArray[position]);
        intentFruitSpecificInfo.putExtra(RECIPE_NAME, recipeArray[position]);
        intentFruitSpecificInfo.putExtra(RECIPE_STEPS, stepsArray[position]);

        startActivity(intentFruitSpecificInfo);
    }

    /**
     * onConfigurationChanged : if orientation changes from landscape to portrait or vice-versa
     * then use appropriate mainActivity layout xml file.
     * <ul>
     *     <li>activity_ifruit_app_main_land.xml --> landscape mode</li>
     *     <li>activity_ifruit_app_main.xml --> portrait mode</li>
     * </ul>7
     * @param newConfig
     */
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Log.d(CLASS_NAME, ":\tonConfigurationChanged = Landscape");
            setContentView(R.layout.activity_ifruit_app_main_land);
        }else{
            Log.d(CLASS_NAME, ":\tonConfigurationChanged = Portrait");
            setContentView(R.layout.activity_ifruit_app_main);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ifruit_app_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}