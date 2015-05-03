/**
 *  Student     :   Paula Dwan
 *  Student ID  :   13208660
 *  Course      :   COMP-47330 Practical Android Computing
 *  Assignment  :   1
 *  Due date    :   04-March-2015
 *
 *  Summary     :   Email emulator where
 *                  1a. User launches application - main activity opens : 'ComposeEmailActivity'
 *                  1b. User enters values for fields : 'from', 'to', 'cc', 'bcc', 'subject', and
 *                      'message'.
 *                  2a. User clicks [Send] button and email fields excluding 'bcc' are displayed in
 *                      second activity as read only - 'DisplayEmailActivity'.
 *                  2b. User clicks [Clear] button and all fields are deleted and reset to initial
 *                      blank values.
 *                  3.  In 'DisplayEmailActivity' activity, [Back] button returns user to
 *                      'ComposeEmailActivity' with all original values for each field.
 *                  4.  If user closes application then the fields are populated with most recent
 *                      values.
 *  File        :   DisplayEmailActivity - secondary activity called by ComposeEmailActivity
 */
package org.dwan.paula.a01_emailemulator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

public class DisplayEmailActivity extends ActionBarActivity {

    private static final String CLASS_NAME = "ComposeEmailActivity";

    /**
     * Create layout once activity is implemented.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, " :\tonCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_email);
        Intent displayEmailIntent = getIntent();

        displayEmailIntent(displayEmailIntent);
    }

    /**
     *  Display EMail using values as received from ComposeEmailActivity for the email fields : <br/>
     *  <ul><li>from</li>
     *      <li>to</li>
     *      <li>cc</li>
     *      <li>subject</li>
     *      <li>message</li></ul>
     */
    private void displayEmailIntent(Intent intent) {
        Log.d(CLASS_NAME, " :\tdisplayEmailIntent");

        String from = "";
        String to = "";
        String cc = "";
        String subject = "";
        String message = "";

        if (intent != null) {
            from = intent.getStringExtra(ComposeEmailActivity.EMAIL_FROM);
            to = intent.getStringExtra(ComposeEmailActivity.EMAIL_TO);
            cc = intent.getStringExtra(ComposeEmailActivity.EMAIL_CC);
            subject = intent.getStringExtra(ComposeEmailActivity.EMAIL_SUBJECT);
            message = intent.getStringExtra(ComposeEmailActivity.EMAIL_MESSAGE);
        }

        TextView textViewFrom = (TextView) findViewById(R.id.textViewFromValue);
        textViewFrom.setText(from);
        TextView textViewTo = (TextView) findViewById(R.id.textViewToValue);
        textViewTo.setText(to);
        TextView textViewCc = (TextView) findViewById(R.id.textViewCcValue);
        textViewCc.setText(cc);
        TextView textViewSubject =  (TextView) findViewById(R.id.textViewSubjectValue);
        textViewSubject.setText(subject);
        TextView textViewMessage =  (TextView) findViewById(R.id.textViewMessageValue);
        textViewMessage.setText(message);

    }

    /**
     * Returns to calling activity : <b>ComposeEmailActivity</b>.
     * @param view
     */
    public void backToEMailWriter (View view) {
        Log.d(CLASS_NAME, " :\tbackToEMailWriter");
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_display_email, menu);
        return super.onCreateOptionsMenu(menu);
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