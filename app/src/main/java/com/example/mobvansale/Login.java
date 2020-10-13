package com.example.mobvansale;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private Button btnsignup;
    private Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getApplicationContext());
        btnsignup = (Button) findViewById(R.id.bsignup);
        btnlogin = (Button) findViewById(R.id.login);


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
                login();
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