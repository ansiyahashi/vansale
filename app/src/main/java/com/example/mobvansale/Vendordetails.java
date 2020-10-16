package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Vendordetails extends AppCompatActivity implements OncompletCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendordetails);

        List<String> categories = new ArrayList<String>();
        Addcusvenddata addcusvenddata = new Addcusvenddata(this);
        List<Addcusvenddatamodel> addcusvenddatamodels=addcusvenddata.selectAllVendors();

        for (Addcusvenddatamodel addcusvenddatamodel:addcusvenddatamodels){
            categories.add(addcusvenddatamodel.getCvname());
        }




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        MyListAdapter adapter = new MyListAdapter(addcusvenddatamodels,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onclick(Object data) {

    }

    @Override
    public void onQtyclick(int qty, String price) {

    }
}
