package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;

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

public class Cusvend extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button ok;
    Button cancel ;
    EditText cvname;
    EditText shname;
    EditText address;
    EditText email;
    EditText phno;

    EditText discount;
    Addcusvenddata addcusvenddata1;
    public String selectedroute;
    public String cus_vend;
    public String status;
    public String creditbal;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusvend);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        Routeinsertion routeinsertion = new Routeinsertion(this);
        List<Routeinsertionmodel> routeinsertionmodels=routeinsertion.selectAllRoutes();

        for (Routeinsertionmodel routeinsertionmodel:routeinsertionmodels){
            categories.add(routeinsertionmodel.getRname());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        final RadioButton rdb1 = (RadioButton) findViewById(R.id.rcustomer);
        rdb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                   cus_vend="customer" ;
                }
                else{
                    // Do your coding
                }
            }
        });
        final RadioButton rdb2 = (RadioButton) findViewById(R.id.rvendor);
        rdb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    cus_vend="vendor" ;
                }
                else{
                    // Do your coding
                }
            }
        });


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
                if (cvname.getText().toString().isEmpty() || address.getText().toString().isEmpty() || shname.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phno.getText().toString().isEmpty() || discount.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fullfill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if(rdb1.isChecked()||rdb2.isChecked())
                    {


                    if (phno.getText().toString().length() == 10) {
                        if (email.getText().toString().trim().matches(emailPattern)) {


                            Addcusvenddatamodel addcusvenddatamodel = new Addcusvenddatamodel();

                            addcusvenddatamodel.setCvname(cvname.getText().toString());
                            addcusvenddatamodel.setShname(shname.getText().toString());
                            addcusvenddatamodel.setRid(selectedroute);
                            addcusvenddatamodel.setCust_vend(cus_vend);

                            addcusvenddatamodel.setAddress(address.getText().toString());
                            addcusvenddatamodel.setEmail(email.getText().toString());
                            addcusvenddatamodel.setPhno(phno.getText().toString());
                            addcusvenddatamodel.setDiscount(discount.getText().toString());
                            addcusvenddata1.insert(addcusvenddatamodel);
                            clearAll();
                            Toast.makeText(getApplicationContext(), " Successfully Completed", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            email.setError("Invalid emailid");
                        }
                    } else {
                        phno.setError("Invalid phone number");
                    }

                        }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "please select the customer or vendor field", Toast.LENGTH_LONG).show();
                    }
                }
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
        selectedroute = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + selectedroute, Toast.LENGTH_LONG).show();
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


