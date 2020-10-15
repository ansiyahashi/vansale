package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Customerdetails extends AppCompatActivity {


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdetails);



        List<String> categories = new ArrayList<String>();
            Addcusvenddata addcusvenddata = new Addcusvenddata(this);
            List<Addcusvenddatamodel> addcusvenddatamodels=addcusvenddata.selectAllCustomers();

            for (Addcusvenddatamodel addcusvenddatamodel:addcusvenddatamodels){
                categories.add(addcusvenddatamodel.getCvname());
            }




            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            MyListAdapter adapter = new MyListAdapter(addcusvenddatamodels);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
}