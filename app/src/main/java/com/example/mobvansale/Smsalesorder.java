package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Smsalesorder extends AppCompatActivity implements AdapterView.OnItemSelectedListener,OncompletCallback {
    public String selectedroute;
    public int selectedrouteId;
    Addcusvenddata cusdata;
    List<Routeinsertionmodel> routeinsertionmodels;

    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsalesorder);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
ok=(Button)findViewById(R.id.ok) ;
        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        final Routeinsertion routeinsertion = new Routeinsertion(this);
         routeinsertionmodels=routeinsertion.selectAllRoutes();

        for (Routeinsertionmodel routeinsertionmodel:routeinsertionmodels){
            categories.add(routeinsertionmodel.getRname());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        Routeinsertionmodel routeinsertionmodel=new Routeinsertionmodel();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> categories = new ArrayList<String>();
                Addcusvenddata addcusvenddata = new Addcusvenddata(getApplicationContext());
              //  List<Addcusvenddatamodel> addcusvenddatamodels=addcusvenddata.selectAllCustomers();
               List<Addcusvenddatamodel> addcusvenddatamodels=addcusvenddata.selectCustomers(selectedrouteId);

                for (Addcusvenddatamodel addcusvenddatamodel:addcusvenddatamodels){
                    categories.add(addcusvenddatamodel.getCvname());
                    categories.add(addcusvenddatamodel.getShname());


                }




                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.smrecyclerview);
                MyListAdapter adapter = new MyListAdapter(addcusvenddatamodels,Smsalesorder.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);






            }});





    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        selectedroute = parent.getItemAtPosition(position).toString();
        selectedrouteId=routeinsertionmodels.get(position).getId();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + selectedroute, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onclick(Object data) {
        Addcusvenddatamodel addcusvenddatamodel=(Addcusvenddatamodel)data;
        Intent intent=new Intent(this, CustomerorderView.class);
        intent.putExtra("customerid",addcusvenddatamodel.getId());
        startActivity(intent);
    }

    @Override
    public void onQtyclick(int qty, String price) {

    }
}

// public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// On selecting a spinner item
//  selectedcategory = parent.getItemAtPosition(position).toString();

// Showing selected spinner item
//  Toast.makeText(parent.getContext(), "Selected: " + selectedcategory, Toast.LENGTH_LONG).show();
//  }
//  public void onNothingSelected(AdapterView<?> arg0) {
//    // TODO Auto-generated method stub
// }
// private void loadSpinnerData() {


