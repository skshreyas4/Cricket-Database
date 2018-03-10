package com.example.shreyassk.sqlitedatabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by shreyas s k on 17-02-2018.
 */

public class MainActivity extends AppCompatActivity {
    Button click,add,cancel,retrive;
    AlertDialog alertDialog;
    DatabaseClass databaseClass;
    ArrayList<ModuleClass>arrayList=new ArrayList<>();
    String names[];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        databaseClass=new DatabaseClass(this);
        databaseClass.addDetails("RASHMI",93469409);
        databaseClass.addDetails("VINOD",95853020);
        arrayList=databaseClass.retriveData();
        names=new String[arrayList.size()];
        for(int i=0;i<arrayList.size();i++)
        {
             names[i]=arrayList.get(i).Name;
        }
        ListView lv=(ListView) findViewById(R.id.ListView);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);


    }
}
