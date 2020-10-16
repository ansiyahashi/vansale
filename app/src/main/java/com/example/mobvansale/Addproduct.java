package com.example.mobvansale;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class Addproduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button ok;
    Button cancel ;
    EditText pname;
    EditText catagory;
    EditText qty;
    EditText srate;
    EditText prate;
    EditText edate;

    Productinsertion productinsertion1;
    public String selectedcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);


        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

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
        pname = (EditText) findViewById(R.id.pname);

        qty = (EditText) findViewById(R.id.qty);
        srate = (EditText) findViewById(R.id.srate);
        prate = (EditText) findViewById(R.id.prate);
        edate=(EditText) findViewById(R.id.edate);
        productinsertion1=new   Productinsertion(getApplicationContext());
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pname.getText().toString().isEmpty() || selectedcategory.isEmpty() || qty.getText().toString().isEmpty() || srate.getText().toString().isEmpty() || prate.getText().toString().isEmpty() || edate.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "please fill all fields", Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                        Productinsertionmodel productinsertionmodel = new Productinsertionmodel();
                       productinsertionmodel.setPname(pname.getText().toString());
                     productinsertionmodel.setCatogry(selectedcategory);
                     productinsertionmodel.setQty(qty.getText().toString());
                    productinsertionmodel.setSrate(srate.getText().toString());
                    productinsertionmodel.setPrate(prate.getText().toString());
                    productinsertionmodel.setEdate(edate.getText().toString());
                   productinsertion1.insert(productinsertionmodel);
                         Toast.makeText(getApplicationContext(), "product adding Successfully Completed", Toast.LENGTH_LONG).show();


                        finish();
                    }
                   // else
                  //  {
                   //
                //    Toast toast = Toast.makeText(getApplicationContext(), "enter valid quantity", Toast.LENGTH_LONG);
                   //     toast.show();
                   // }
                }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent=new Intent(Addproduct.this, Updateproduct.class);
                //startActivity(intent);
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
    public void productinsertion() {

        Intent intent = new Intent(Addproduct.this,Productinsertion.class);
        startActivity(intent);

    }
    private void clearAll() {
        pname.getText().clear();
        // cataogory.getText().clear();
        qty.getText().clear();
        srate.getText().clear();
        prate.getText().clear();
        edate.getText().clear();
    }

}
