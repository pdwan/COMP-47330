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

File :  FruitAppSpecificInfo - secondary activity
**/

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import android.util.Log;

public class FruitAppSpecificInfo extends ActionBarActivity {

    private static final String CLASS_NAME = "FruitAppSpecificInfo";

    /**
     * <b>onCreate </b>: first implementation of secondary activity which displays more information
     * on the fruit item selected in main activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, ":\tonCreate");

        super.onCreate(savedInstanceState);
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();

        if (orientation == Surface.ROTATION_90 || orientation == Surface.ROTATION_270) {
            Log.d(CLASS_NAME, " :\tonCreate = Landscape");
            setContentView(R.layout.activity_ifruit_app_more_info_land);
        } else {
            Log.d(CLASS_NAME, " :\tonCreate = Portrait");
            setContentView(R.layout.activity_ifruit_app_more_info);
        }

        Intent fruitInfoIntent = getIntent();
        populateFruitIntent(fruitInfoIntent);
    }

    /**
     * <b>populateFruitIntent</b> : populates the gridView with the available extra information on
     * the fruit selected in main activity.
     * @param intent Intent containing data specific to fruit selected in main activity
     */
    private void populateFruitIntent(Intent intent) {
        Log.d(CLASS_NAME, ":\tpopulateFruitIntent");

        String fruit = "";
        String desc = "";
        String image = "";
        String recipe = "";
        String steps = "";
        int imageID = -1;

        if (intent!= null) {
            fruit = intent.getStringExtra(FruitAppMain.FRUIT_NAME);
            desc = intent.getStringExtra(FruitAppMain.FRUIT_DESC);
            image = intent.getStringExtra(FruitAppMain.FRUIT_IMAGE);
            imageID = getResources().getIdentifier(image, "drawable", getPackageName());
            recipe = intent.getStringExtra(FruitAppMain.RECIPE_NAME);
            steps = intent.getStringExtra(FruitAppMain.RECIPE_STEPS);
        }

        TextView tvFruit = (TextView) findViewById(R.id.textNameInfo);
        tvFruit.setText(Html.fromHtml(fruit));
        TextView tvDesc = (TextView) findViewById(R.id.textDescInfo);
        tvDesc.setText(Html.fromHtml(desc));
        ImageView ivImage = (ImageView) findViewById(R.id.textImageInfo);
        ivImage.setImageResource(imageID);
        TextView tvRecipe = (TextView) findViewById(R.id.textRecipeInfo);
        tvRecipe.setText(Html.fromHtml(recipe));
        TextView tvSteps = (TextView) findViewById(R.id.textStepsInfo);
        tvSteps.setText(Html.fromHtml(steps));
        Log.d(CLASS_NAME, ":\tpopulateFruitIntent - image = " + image + " & imageID = " + imageID);
    }

    /**
     * onConfigurationChanged : if orientation changes from landscape to portrait or vice-versa
     * then use appropriate mainActivity layout xml file.
     * <ul>
     *     <li>activity_ifruit_app_main_land.xml --> landscape mode</li>
     *     <li>activity_ifruit_app_main.xml --> portrait mode</li>
     * </ul>
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

    /**
     * <b>backToFruitListing</b> : returns user to main activity
     * @param view
     */
    public void backToFruitListing (View view) {
        Log.d(CLASS_NAME, ":\tbackToFruitListing");

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ifruit_app_more_info, menu);
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