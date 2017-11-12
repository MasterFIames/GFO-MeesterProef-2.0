package com.gfo.gfo_meesterproef.Admin.ViewAccountBackgroundworker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gfo.gfo_meesterproef.Admin.ViewAccountBackgroundworker.ViewUsername;
import com.gfo.gfo_meesterproef.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ViewAccountActivity extends AppCompatActivity {

    ListView AccountsList;
    public static Context AccountContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

//        Gets usernames
        String type = "view";
        String username = null;
        try {
            username = new ViewUsername(this).execute(type).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        SharedPreferences usernamePref = getSharedPreferences("usernamePreference", AccountContext.MODE_PRIVATE);
        usernamePref.edit().putString("username", username);

//        Gets Passwords
        String type2 = "view";
        String password = null;
        try {
            password = new ViewPassword(this).execute(type2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        SharedPreferences passwordPref = getSharedPreferences("passwordPreference", AccountContext.MODE_PRIVATE);
        passwordPref.edit().putString("password", password);

//        Gets Email
        String type3 = "view";
        String email = null;
        try {
            email = new ViewEmail(this).execute(type3).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        SharedPreferences emailPref = getSharedPreferences("emailPreference", AccountContext.MODE_PRIVATE);
        emailPref.edit().putString("email", email);



////        fill listView with (array)List
//        AccountsList = (ListView) findViewById(R.id.AccountsList);
//        ArrayAdapter<String> accountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accounts);
//        AccountsList.setAdapter(accountAdapter);
//    }
//

    }
}
