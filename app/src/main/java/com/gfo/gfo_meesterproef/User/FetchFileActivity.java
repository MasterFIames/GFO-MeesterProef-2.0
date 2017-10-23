package com.gfo.gfo_meesterproef.User;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gfo.gfo_meesterproef.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.gfo.gfo_meesterproef.LoginActivity.contextOfApplication;

public class FetchFileActivity extends AppCompatActivity {

    ListView userProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_file);

//        get saved username
        SharedPreferences usernamePref = getSharedPreferences("usernamePreference", contextOfApplication.MODE_PRIVATE);
        String username = usernamePref.getString("username", "");

//        get selected group from FetchGroupActivity
        String group = getIntent().getExtras().getString("userGroup","");

//        contact database for products
        String type = "fetch";
        List<String> products = new ArrayList<String>();
        try {
            products = new FetchFile(this).execute(type, username, group).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        fill listView with List
        userProductList = (ListView) findViewById(R.id.userProductsList);
        ArrayAdapter<String> productAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, products);
        userProductList.setAdapter(productAdapter);
    }
}