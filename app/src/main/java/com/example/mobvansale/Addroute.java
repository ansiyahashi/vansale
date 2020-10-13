package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addroute extends AppCompatActivity {
Button  ok;
Button clear;
EditText routename;
EditText landmark;
 Routeinsertion Routeinsertion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroute);
        ok=(Button)findViewById(R.id.bok);
        clear=(Button)findViewById(R.id.bclear);
        routename=(EditText)findViewById(R.id.rname);
        landmark=(EditText)findViewById(R.id.landmark);
        Routeinsertion=new Routeinsertion(getApplicationContext());
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (routename.getText().toString().isEmpty()||landmark.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fullfill details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Routeinsertionmodel routeinsertionmodel=new Routeinsertionmodel();


                    routeinsertionmodel.setrname(routename.getText().toString());
                    routeinsertionmodel.setLandmark(landmark.getText().toString());
                    Routeinsertion.insert(routeinsertionmodel);
                    clearAll();
                    Toast.makeText(getApplicationContext(), "Registration Successfully Completed", Toast.LENGTH_SHORT).show();
                    finish();
                }
                }

        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });
    }
    private void clearAll() {
        routename.getText().clear();
        landmark.getText().clear();

    }

}