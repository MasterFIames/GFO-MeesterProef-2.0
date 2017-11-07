package com.gfo.gfo_meesterproef.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import com.gfo.gfo_meesterproef.R;

public class AddAccountActivity extends AppCompatActivity {

    EditText usernamecET, passwordcET, emailcET, admincET;
    CheckBox AdminCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        usernamecET = (EditText) findViewById(R.id.editTextUsername);
        passwordcET = (EditText) findViewById(R.id.editTextPassword);
        emailcET = (EditText) findViewById(R.id.editTextEmail);
        AdminCheck = (CheckBox) findViewById(R.id.CheckboxAdmin);
    }

    public void addUser(View view) {
        String usernamec = usernamecET.getText().toString();
        if (TextUtils.isEmpty(usernamec)) {
            usernamecET.setError("Username can't be empty");
            return;
        }
        String passwordc = passwordcET.getText().toString();
        if (TextUtils.isEmpty(passwordc)) {
            passwordcET.setError("Password can't be empty");
            return;
        }
        String emailc = emailcET.getText().toString();
        if (TextUtils.isEmpty(emailc)) {
            emailcET.setError("E-mail can't be empty");
            return;
        } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailc).matches()) {
            return;
        }
//        else {
//            emailcET.setError("Please enter a valid e-mail adress");
//            return;
//        }

        if (((CheckBox) AdminCheck).isChecked()) ;
        String adminflagc;
        {
            adminflagc = "Y";
        }
        String type = "add_account";

//        stuurt data naar backgroundworker
        AddAccountBackgroundWorker backgroundWorker = new AddAccountBackgroundWorker(this);
        backgroundWorker.execute(type, usernamec, passwordc, emailc, adminflagc);
    }

    public void reset (View view) {
//        reset de edittext na een account toegevoegd te hebben
        ((EditText) findViewById(R.id.editTextUsername)).setText("");
        ((EditText) findViewById(R.id.editTextPassword)).setText("");
        ((EditText) findViewById(R.id.editTextEmail)).setText("");
        ((CheckBox) findViewById(R.id.CheckboxAdmin)).setChecked(false);
    }



}

