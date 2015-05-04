package org.dwan.paula.lunarenterprises.customer;

/*
    Student Paula Dwan
    ID      13208660
    Module  COMP-47330 - Practical Android Programming
    Course  MSc Advanced Software Engineering

    File    RegisterCustomerDetailsActivity.java
            THis activity covers registration of a new user/customer details and personal
            preferences for future contact..
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.dwan.paula.lunarenterprises.R;

public class RegisterCustomerDetailsActivity extends ActionBarActivity {

    public static final String CLASS_NAME = "RegisterCustomer";
    public static final String CUST_NAME1 = "custKeyName1";
    public static final String CUST_NAME2 = "custKeyName2";
    public static final String CUST_ADDRESS1 = "custKeyAddress1";
    public static final String CUST_ADDRESS2 = "custKeyAddress2";
    public static final String CUST_ADDRESS3 = "custKeyAddress3";
    public static final String CUST_ADDRESS4 = "custKeyAddress4";
    public static final String CUST_MOBILE = "custKeyMobile";
    public static final String CUST_EMAIL = "custKeyEmail";
    public static final String CUST_MOBILE_OKAY = "custKeyMobileOkay";
    public static final String CUST_EMAIL_OKAY = "custKeyEmailOkay";
    public static final String CUST_3PARTY_OKAY = "custKey3partyOkay";

    private EditText etName1, etName2, etAddress1, etAddress2, etAddress3, etAddress4, etMobile, etEmail;
    private CheckBox chkAllowSMS, chkAllowEmail, chkAllow3Party;
    private Button btnRegister;
    private String smsOkay = "0", emailOkay = "0", thirdPartyOkay = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "\t: on Create - customer details ...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer_details);

        populateActivityFields();
    }

    /**
     * populateActivityFields : populates the EditText fields, CheckBox values and Button values
     * with those entered by the user.
     */
    private void populateActivityFields() {
        Log.d(CLASS_NAME, "\t: populateActivityFields : with values as entered by user ...");

        etName1 = (EditText) findViewById(R.id.edittextCustomerNameFirst);
        etName2 = (EditText) findViewById(R.id.edittextCustomerNameFamily);
        etAddress1 = (EditText) findViewById(R.id.edittextCustomerAddress1);
        etAddress2 = (EditText) findViewById(R.id.edittextCustomerAddress2);
        etAddress3 = (EditText) findViewById(R.id.edittextCustomerAddress3);
        etAddress4 = (EditText) findViewById(R.id.edittextCustomerAddress4);
        etAddress4 = (EditText) findViewById(R.id.edittextCustomerAddress4);
        etMobile = (EditText) findViewById(R.id.edittextCustomerMobile);
        etEmail = (EditText) findViewById(R.id.edittextCustomerEmail);

        chkAllowSMS = (CheckBox) findViewById(R.id.chkCustomerMobile);
        chkAllowEmail = (CheckBox) findViewById(R.id.chkCustomerEmail);
        chkAllow3Party = (CheckBox) findViewById(R.id.chk3rdParty);

        chkAllowSMS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) smsOkay = "1";
            }
        });
        chkAllowEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) emailOkay = "1";
            }
        });
        chkAllow3Party.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) thirdPartyOkay = "1";
            }
        });

        btnRegister = (Button) findViewById(R.id.buttonRegisterSubmit);
        btnRegister.setEnabled(false);

        if (validateAnyEditFieldUsed(new EditText[]{etName1, etName2, etAddress1, etAddress2, etAddress3, etAddress4, etMobile, etEmail})) {
            if (validateActivityFields()) btnRegister.setEnabled(true);
        }
    }

    /**
     * Accept and save customer details : handle Click Event of <b>Submit</b> button, call new layout and
     * save customer details pending creation of user name and login password.
     *
     * @param view
     */
    public void SubmitCustomerContactDetails(View view) {
        Log.d(CLASS_NAME, "\t: accept and save customer details, call new layout ...");

        Intent intentRegisterDetails = new Intent(getApplicationContext(), RegisterSignInActivity.class);
        intentRegisterDetails.putExtra(CUST_NAME1, etName1.toString());
        intentRegisterDetails.putExtra(CUST_NAME2, etName2.toString());
        intentRegisterDetails.putExtra(CUST_ADDRESS1, etAddress1.toString());
        intentRegisterDetails.putExtra(CUST_ADDRESS2, etAddress2.toString());
        intentRegisterDetails.putExtra(CUST_ADDRESS3, etAddress3.toString());
        intentRegisterDetails.putExtra(CUST_ADDRESS4, etAddress4.toString());
        intentRegisterDetails.putExtra(CUST_EMAIL, etEmail.toString());
        intentRegisterDetails.putExtra(CUST_MOBILE, etMobile.toString());
        intentRegisterDetails.putExtra(CUST_EMAIL_OKAY, emailOkay.toString());
        intentRegisterDetails.putExtra(CUST_MOBILE_OKAY, smsOkay.toString());
        intentRegisterDetails.putExtra(CUST_3PARTY_OKAY, thirdPartyOkay.toString());

        startActivity(intentRegisterDetails);
    }

    /**
     * delete / clear all customer details : handle Click Event of <b>Clear All</b> button,
     * basically reset all fields.
     *
     * @param view
     */
    public void ClearAllCustomerContactDetails(View view) {
        Log.d(CLASS_NAME, "\t: clear all customer details as entered ...");

        etName1.getText().clear();
        etName2.getText().clear();
        etAddress1.getText().clear();
        etAddress2.getText().clear();
        etAddress3.getText().clear();
        etAddress4.getText().clear();
        etMobile.getText().clear();
        etEmail.getText().clear();
        chkAllowSMS.setChecked(false);
        chkAllowEmail.setChecked(false);
        chkAllow3Party.setChecked(false);
        smsOkay = "0";
        emailOkay = "0";
        thirdPartyOkay = "0";
    }

    /**
     * Creates action bar for inclusion of overflow menu.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(CLASS_NAME, "\t: onCreateOptionsMenu - Action Bar ...");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.app_logo);
        actionBar.setDisplayUseLogoEnabled(true);

        return true;
    }

    /**
     * Create and populate overflow menu in Action Bar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(CLASS_NAME, "\t: onOptionsItemSelected - Action Bar ...");

        switch (item.getItemId()) {
            case R.id.overflow_close:
                onDestroy();
                break;
            case R.id.overflow_deregister:
                // DatabaseAdapter databaseAdapter ;
                // DeRegisterCustomer deRegisterCustomer = new DeRegisterCustomer();
                // DeRegisterCustomer.removeCustomerFromTable(userName);
                Toast.makeText(this, CustomerError.ERROR9001, Toast.LENGTH_SHORT).show();
                break;
            case R.id.overflow_help:
                Toast.makeText(this, CustomerError.ERROR9002, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    // VALIDATION Methods

    private boolean validateActivityFields() {

        boolean isNotValid = false;

        int maxLengthName = 40;
        int maxLengthAddress = 50;
        int maxLengthMobile = 11;
        int maxLengthEmail = 50;

        final String REGEX_PHONE = "[08]\\d[-|\u2013]\\d{7}";

        String custFirstName, custFamilyName, custAddress1, custAddress2, custAddress3, custAddress4, custMobile, custEmail;
        custFirstName = etName1.getText().toString();
        custFamilyName = etName2.getText().toString();
        custAddress1 = etAddress1.getText().toString();
        custAddress2 = etAddress2.getText().toString();
        custAddress3 = etAddress3.getText().toString();
        custAddress4 = etAddress4.getText().toString();
        custMobile = etMobile.getText().toString();
        custEmail = etEmail.getText().toString();

        //<TODO> add factory
        if (exceedsMaximumLength(custFirstName.length(), maxLengthName)) {        // First/use name validation
            etName1.setError(CustomerError.ERROR8001);
            clearEditField(etName1);
            isNotValid = true;
        } else if (isNullField(custFirstName.length())) {
            etName1.setError(CustomerError.ERROR4001);
            clearEditField(etName1);
            isNotValid = true;
        } else {
            etName1.setError(null);
            isNotValid = false;
        }
        if (exceedsMaximumLength(custFamilyName.length(), maxLengthName)) {        // Surname/family name validation
            etName2.setError(CustomerError.ERROR8002);
            clearEditField(etName2);
            isNotValid = true;
        } else if (isNullField(custFamilyName.length())) {
            etName2.setError(CustomerError.ERROR4001);
            clearEditField(etName2);
            isNotValid = true;
        } else {
            etName2.setError(null);
            isNotValid = false;
        }
        if (exceedsMaximumLength(custAddress1.length(), maxLengthAddress)) {  // address 1 --> validation
            etAddress1.setError(CustomerError.ERROR8003);
            clearEditField(etAddress1);
            isNotValid = true;
        } else if (isNullField(custAddress1.length())) {
            etAddress1.setError(CustomerError.ERROR4001);
            clearEditField(etAddress1);
            isNotValid = true;
        } else {
            etAddress1.setError(null);
            isNotValid = false;
        }
        if (exceedsMaximumLength(custAddress2.length(), maxLengthAddress)) {  // address 2 --> validation
            etAddress2.setError(CustomerError.ERROR8003);
            clearEditField(etAddress2);
            isNotValid = true;
        } else if (isNullField(custAddress2.length())) {
            etAddress2.setError(CustomerError.ERROR4001);
            clearEditField(etAddress2);
            isNotValid = true;
        } else {
            etAddress2.setError(null);
            isNotValid = false;
        }
        if (isNullField(custAddress3.length())) {                             // address 3 --> validation
            etAddress3.setError(null);                                      // optional null field
            isNotValid = false;
        } else if (exceedsMaximumLength(custAddress3.length(), maxLengthAddress)) {
            etAddress3.setError(CustomerError.ERROR8003);
            clearEditField(etAddress3);
            isNotValid = true;
        }
        if (exceedsMaximumLength(custAddress4.length(), maxLengthAddress)) {  // address 4 --> validation
            etAddress4.setError(CustomerError.ERROR8003);
            clearEditField(etAddress4);
            isNotValid = true;
        } else if (isNullField(custAddress4.length())) {
            etAddress4.setError(CustomerError.ERROR4001);
            clearEditField(etAddress4);
            isNotValid = true;
        } else {
            etAddress4.setError(null);
            isNotValid = false;
        }
        if (isNullField(custMobile.length()) && isCheckBoxSelected(chkAllowSMS)) {    // Mobile & SMS Checkbox
            etMobile.setError(CustomerError.ERROR5001);
            chkAllowSMS.setChecked(false);
            clearEditField(etMobile);
            isNotValid = true;
        } else if (!isNullField(custMobile.length()) && !isCheckBoxSelected(chkAllowSMS)
                && exceedsMaximumLength(etMobile.length(), maxLengthMobile)) {
            etMobile.setError(CustomerError.ERROR8004);
            clearEditField(etMobile);
            chkAllowSMS.setChecked(false);
            isNotValid = true;
        } else if (!isNullField(custMobile.length()) && !isCheckBoxSelected(chkAllowSMS)
                && isNotValidFormatPhone(custMobile.toString(), REGEX_PHONE)) {
            etMobile.setError(CustomerError.ERROR3003);
            clearEditField(etMobile);
            chkAllowSMS.setChecked(false);
            isNotValid = true;
        } else
            etMobile.setError(null);
        if (isNullField(custEmail.length()) && isCheckBoxSelected(chkAllowEmail)) {    // EMail & EMail Checkbox
            etEmail.setError(CustomerError.ERROR5002);
            chkAllowEmail.setChecked(false);
            clearEditField(etMobile);
            isNotValid = true;
        } else if (!isNullField(custEmail.length()) && !isCheckBoxSelected(chkAllowEmail)
                && exceedsMaximumLength(etEmail.length(), maxLengthEmail)) {
            etEmail.setError(CustomerError.ERROR8005);
            clearEditField(etMobile);
            chkAllowEmail.setChecked(false);
            isNotValid = true;
        } else if (!isNullField(custEmail.length()) && !isCheckBoxSelected(chkAllowEmail)
                && isNotValidFormatEmail(etEmail.toString())) {
            etEmail.setError(CustomerError.ERROR3004);
            clearEditField(etMobile);
            chkAllowEmail.setChecked(false);
            isNotValid = true;
        } else {
            etEmail.setError(null);
            isNotValid = false;
        }
        return isNotValid;
    }

    private boolean validateAnyEditFieldUsed(EditText[] allEditFields) {
        for (int i = 0; i < allEditFields.length; i++) {
            EditText currentEditField = allEditFields[i];
            return (currentEditField.getText().toString().length() > 0) ? true : false;
        }
        return false;
    }

    private boolean exceedsMaximumLength(int fieldLength, int maxLength) {
        return (fieldLength > maxLength);
    }

    private boolean isNullField(int fieldLength) {
        return (fieldLength == 0);
    }

    private boolean isCheckBoxSelected(CheckBox cb) {
        return (cb.isChecked());
    }

    private boolean isNotValidFormatEmail(String em) {
        return Patterns.EMAIL_ADDRESS.matcher(em).matches();
    }

    private boolean isNotValidFormatPhone(String tel, String telRegex) {
        return Patterns.PHONE.matcher(tel).matches() && tel.matches(telRegex);
    }

    private boolean isNotValidFormatRegex(String fieldValue, String fieldRegex) {
        return fieldValue.matches(fieldRegex);
    }

    private void clearEditField(EditText et) {
        et.getText().clear();
    }
}