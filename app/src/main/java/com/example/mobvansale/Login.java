package com.example.mobvansale;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Login extends AppCompatActivity {
    private Button btnsignup;
    private Button btnlogin;
EditText username;
    EditText password;
    List<RegistrationModel> registrationModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getApplicationContext());
        btnsignup = (Button) findViewById(R.id.bsignup);
        btnlogin = (Button) findViewById(R.id.login);
        username=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getApplicationContext());
                signup();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(username.getText().toString().equalsIgnoreCase("a") && password.getText().toString().equalsIgnoreCase("a")) {
                    login();
                }
else {

                    registration registration=new registration(getApplicationContext());
                    RegistrationModel registrationModel= registration.selectdata(username.getText().toString());
                    if (registrationModel==null) {
                        Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(password.getText().toString().equalsIgnoreCase( registrationModel.getPassword())){
                            Intent intent = new Intent(Login.this, Homesalesman.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                }


                //Routeinsertion routeinsertion = new Routeinsertion(this);

//









                //login();
            }
        });
    }
        public void signup()
        {

            Intent intent = new Intent(Login.this, signup.class);
            startActivity(intent);
        }

    public void login()
    {

        Intent intent = new Intent(Login.this, Homeowner.class);
        startActivity(intent);
    }

}