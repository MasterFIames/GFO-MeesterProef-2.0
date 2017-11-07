package com.gfo.gfo_meesterproef.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gfo.gfo_meesterproef.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.gfo.gfo_meesterproef.Admin.ViewGroupActivity.groupContext;

public class ViewFileActivity extends AppCompatActivity {

    public static Context fileContext;

    String clickedFile;
    ListView adminProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);
        fileContext = getApplicationContext();

//        get selected group from ViewGroupActivity
        SharedPreferences groupPref = getSharedPreferences("groupPreference", groupContext.MODE_PRIVATE);
        String group = groupPref.getString("group", "");

        //contact database for products
        String type = "view";
        List<String> products = new ArrayList<>();
        try {
            products = new ViewFileBackgroundWorker(this).execute(type, group).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace(); }

        // fill listView with List
        adminProductList = (ListView) findViewById(R.id.adminProductsList);
        ArrayAdapter<String> productAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        adminProductList.setAdapter(productAdapter);

        registerFileClickCallBack();
    }

    private void registerFileClickCallBack() {
        adminProductList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClicked, int position, long id) {
                SharedPreferences clickedFilePref = getSharedPreferences("clickedFilePreference" ,fileContext.MODE_PRIVATE);
                TextView textView = (TextView) viewClicked;
                clickedFile = textView.getText().toString();
//                Fill groupPref
                clickedFilePref.edit().putString("clickedFile", clickedFile).apply();
                Intent i = new Intent(ViewFileActivity.this, OpenFileActivity.class);
                startActivity(i);
            }


}



        );
    }

