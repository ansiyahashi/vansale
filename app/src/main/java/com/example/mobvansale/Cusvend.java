package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cusvend extends AppCompatActivity {
    Spinner spinner;
    Button ok;
    Button cancel ;
    EditText cvname;
    EditText shname;
    EditText address;
    EditText email;
    EditText phno;
    EditText discount;
    Addcusvenddata addcusvenddata1;
    public String selectedcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcusvend);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();

        categories.add("Bread");
        categories.add("Buiscut");
        categories.add("Ben");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);








        cancel= (Button) findViewById(R.id.bcancel);
        ok = (Button) findViewById(R.id.bok);
        cvname=(EditText)findViewById(R.id.name);
        shname=(EditText)findViewById(R.id.shopname);
        address=(EditText)findViewById(R.id.address);
        email=(EditText)findViewById(R.id.email);
        phno=(EditText)findViewById(R.id.phno);
        discount=(EditText)findViewById(R.id.discount);
        addcusvenddata1=new Addcusvenddata(getApplicationContext());
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Addcusvenddatamodel addcusvenddatamodel=new Addcusvenddatamodel();

                addcusvenddatamodel.setcvname(cvname.getText().toString());
                addcusvenddatamodel.setShname(shname.getText().toString());
                addcusvenddatamodel.setRoute(selectedcategory);
                addcusvenddatamodel .setAddress(address.getText().toString());
                addcusvenddatamodel.setEmail(email.getText().toString());
                addcusvenddatamodel.setPhno(phno.getText().toString());
                addcusvenddatamodel.setDiscount(discount.getText().toString());
                addcusvenddata1.insert(addcusvenddatamodel);
                clearAll();
                Toast.makeText(getApplicationContext(), "Registration Successfully Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        selectedcategory = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + selectedcategory, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private void clearAll() {
        cvname.getText().clear();
        address.getText().clear();
        email.getText().clear();
        phno.getText().clear();
        shname.getText().clear();
        discount.getText().clear();
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


