/**
 *  Student     : Paula Dwan
 *  Student ID  : 13208660
 *  Course      : COMP-47330 Practical Android Computing
 *  Assignment  : 1
 *  Due date    : 04-March-2015
 *
 *  Summary     : Email emulator where
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
 *  File        :   ComposeEmailActivity - main activity
 */
package org.dwan.paula.a01_emailemulator;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

public class ComposeEmailActivity extends ActionBarActivity {

    TextView emailFrom;
    TextView emailTo;
    TextView emailCC;
    TextView emailBCC;
    TextView emailSubject;
    TextView emailMessage;

    private static final String CLASS_NAME = "ComposeEmailActivity";

    public static final String MY_PREFERENCES = "MyPrefs" ;
    public static final String EMAIL_FROM = "fromKey";
    public static final String EMAIL_TO= "toKey";
    public static final String EMAIL_CC = "ccKey";
    public static final String EMAIL_BCC = "bccKey";
    public static final String EMAIL_SUBJECT = "subjectKey";
    public static final String EMAIL_MESSAGE = "messageKey";

    SharedPreferences sharedPreferences;

    /**
     * Create layout once activity is implemented.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, " :\tonCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_email);

        populateEditFields();
        populateSharedPreferenceUsingEditFields();
    }

    /**
     * Updates each TextEdit field for the EMail Emulator.
     */
    private void populateEditFields() {
        Log.d(CLASS_NAME, " :\tpopulateEditFields");

        emailFrom = (TextView) findViewById(R.id.editTextEmailFrom);
        emailTo = (TextView) findViewById(R.id.editTextEmailTo);
        emailCC = (TextView) findViewById(R.id.editTextEmailCc);
        emailBCC = (TextView) findViewById(R.id.editTextEmailBcc);
        emailSubject = (TextView) findViewById(R.id.editTextEmailSubject);
        emailMessage = (TextView) findViewById(R.id.editTextEmailMessage);
    }

    /**
     * Updates each field of the EMail Emulator using sharedPreferences and thus stored values,
     * if applicable.
     */
    private void populateSharedPreferenceUsingEditFields() {
        Log.d(CLASS_NAME, " :\tpopulateSharedPreferenceUsingEditFields");

        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(EMAIL_FROM)) {
            emailFrom.setText(sharedPreferences.getString(EMAIL_FROM, ""));
        }
        if (sharedPreferences.contains(EMAIL_TO)) {
            emailTo.setText(sharedPreferences.getString(EMAIL_TO, ""));
        }
        if (sharedPreferences.contains(EMAIL_CC)) {
            emailCC.setText(sharedPreferences.getString(EMAIL_CC, ""));
        }
        if (sharedPreferences.contains(EMAIL_BCC)) {
            emailBCC.setText(sharedPreferences.getString(EMAIL_BCC, ""));
        }
        if (sharedPreferences.contains(EMAIL_SUBJECT)) {
            emailSubject.setText(sharedPreferences.getString(EMAIL_SUBJECT, ""));
        }
        if (sharedPreferences.contains(EMAIL_MESSAGE)) {
            emailMessage.setText(sharedPreferences.getString(EMAIL_MESSAGE, ""));
        }
    }

    /**
     * Saves values of each edit field for the Email Emulator so all are visible in the
     * second activity <b>DisplayEmailActivity</b> when called via Intent. <br/>
     * Also save values of each edit field for the Email Emulator using <b><i>SharedPreferences</i></b> & <b><i>Editor</i></b>
     * so all values are retained when the application is closed/re-opened. <br/>
     * Method is instigated when <b>[Send]</b> button is clicked.
     * @param view
     */
    public void sendWrittenEmail (View view) {
        Log.d(CLASS_NAME, " :\tsendWrittenEmail");

        String from = emailFrom.getText().toString();
        String to = emailTo.getText().toString();
        String cc = emailCC.getText().toString();
        String bcc = emailBCC.getText().toString();
        String subject = emailSubject.getText().toString();
        String message = emailMessage.getText().toString();

        Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_FROM, from);
        editor.putString(EMAIL_TO, to);
        editor.putString(EMAIL_CC, cc);
        editor.putString(EMAIL_BCC, bcc);
        editor.putString(EMAIL_SUBJECT, subject);
        editor.putString(EMAIL_MESSAGE, message);
        editor.apply();

        Intent composeEmailIntent = new Intent(this, org.dwan.paula.a01_emailemulator.DisplayEmailActivity.class);
        composeEmailIntent.putExtra(EMAIL_FROM, from);
        composeEmailIntent.putExtra(EMAIL_TO, to);
        composeEmailIntent.putExtra(EMAIL_CC, cc);
        composeEmailIntent.putExtra(EMAIL_SUBJECT, subject);
        composeEmailIntent.putExtra(EMAIL_MESSAGE, message);
        startActivity(composeEmailIntent);
    }

    /**
     * Clears and thus thus resets the values of each edit field for the Email Emulator. <br/>
     * Method instigated when <b>[Clear]</b> button is clicked.
     * @param view
     */
    public void clearWrittenEmail (View view) {
        Log.d(CLASS_NAME, " :\tclearWrittenEmail");

        final String BLANK_EMAIL_TEXT = "";

        emailFrom.setText(BLANK_EMAIL_TEXT);
        emailTo.setText(BLANK_EMAIL_TEXT);
        emailCC.setText(BLANK_EMAIL_TEXT);
        emailBCC.setText(BLANK_EMAIL_TEXT);
        emailSubject.setText(BLANK_EMAIL_TEXT);
        emailMessage.setText(BLANK_EMAIL_TEXT);

        Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_FROM, BLANK_EMAIL_TEXT);
        editor.putString(EMAIL_TO, BLANK_EMAIL_TEXT);
        editor.putString(EMAIL_CC, BLANK_EMAIL_TEXT);
        editor.putString(EMAIL_BCC, BLANK_EMAIL_TEXT);
        editor.putString(EMAIL_SUBJECT, BLANK_EMAIL_TEXT);
        editor.putString(EMAIL_MESSAGE, BLANK_EMAIL_TEXT);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compose_email, menu);
        return true;
    }

    // VALIDATION STARTED - not yet implemented.


    /*
    * Validate format of EditText field value as entered by user.
    *
    * @param etValue EditText value as entered by user
    * @return true if matches using Patterns.EMAIL_ADDRESS
    */
    private static boolean isValidEmailValue (String etValue) {
        Log.d(CLASS_NAME, " :\tisValidValue for :"+etValue);

        return android.util.Patterns.EMAIL_ADDRESS.matcher(etValue).matches();
    }

    /*
    * Validate length of EditText field > 0.
    * @param etValue EditText value as entered by user
    * @return boolean true for valid false for invalid
    */
    private static boolean isValidValueLength (String etValue) {
        Log.d(CLASS_NAME, " :\tisValidValueLength for : "+etValue);

        boolean isValid = false;

        if (etValue.length() > 0) {
            isValid = true;
        }
        return isValid;
    }

}