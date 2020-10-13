package com.example.mobvansale;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    Button reg;
    Button clr;
    EditText name;
    EditText address;
    EditText email;
    EditText phno;
    EditText pswd;
    EditText cpswd;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    registration registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        reg = (Button) findViewById(R.id.rsubmit);
        clr = (Button) findViewById(R.id.rclear);
        name = (EditText) findViewById(R.id.rname);
        address = (EditText) findViewById(R.id.raddress);
        email = (EditText) findViewById(R.id.remail);
        phno = (EditText) findViewById(R.id.rphno);
        pswd = (EditText) findViewById(R.id.rpsw);
        cpswd=(EditText) findViewById(R.id.rcpsw);
        registration=new registration(getApplicationContext());

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty()||address.getText().toString().isEmpty()||email.getText().toString().isEmpty()||phno.getText().toString().isEmpty()||pswd.getText().toString().isEmpty()||cpswd.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please fullfill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (email.getText().toString().trim().matches(emailPattern)) {
                        if (phno.getText().toString().length() == 10) {
                            if (cpswd.getText().toString().equalsIgnoreCase(pswd.getText().toString())) {
                                RegistrationModel registrationModel = new RegistrationModel();
                                registrationModel.setName(name.getText().toString());
                                registrationModel.setAddress(address.getText().toString());
                                registrationModel.setEmail(email.getText().toString());
                                registrationModel.setPhno(phno.getText().toString());
                                registrationModel.setPassword(pswd.getText().toString());
                                registration.insert(registrationModel);
                                clearAll();
                               Toast.makeText(getApplicationContext(), "Registration Successfully Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                cpswd.setError("Mismatching passwords");
                            }
                        } else {
                            phno.setError("Enter valid PhoneNo");
                        }
                        ;
                    } else {
                        email.setError("enter valid email");

                    }
                }
            }
        });
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });

    }

    private void clearAll() {
        name.getText().clear();
        address.getText().clear();
        email.getText().clear();
        phno.getText().clear();
        pswd.getText().clear();
        cpswd.getText().clear();
    }


    public void registration() {

        Intent intent = new Intent(signup.this, registration.class);
        startActivity(intent);

    }
}