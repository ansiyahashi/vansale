package com.example.mobvansale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Purchasehistory extends AppCompatActivity implements OncompletCallback {
    CalendarView calender;
    TextView date_view;
    Button ok;
    String dt;
    String Date;
    String day;
    String montth;
    int yeaer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasehistory);
        calender = (CalendarView)
                findViewById(R.id.calender);
        date_view = (TextView)
                findViewById(R.id.date_view);
        ok=(Button)findViewById(R.id.ok) ;

        // Add Listener in calendar
        calender
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            // In this Listener have one method
                            // and in this method we will
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {

                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0
                                day=CommonUtils.toString(dayOfMonth);
                                montth=CommonUtils.toString(month+1);
                                yeaer=year;
                                Date
                                        =  "0"+dayOfMonth + "-"
                                        + (month + 1) + "-" + year;

                                // set this date in TextView for Display
                                date_view.setText(Date);
                            }
                        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> categories = new ArrayList<String>();
                OrderLines orderLines = new OrderLines(getApplicationContext());
                //  List<Addcusvenddatamodel> addcusvenddatamodels=addcusvenddata.selectAllCustomers();
                // String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date(Date));
                day=day.length()==1?"0"+day:day;
                montth=montth.length()==1?"0"+montth:montth;

                Date  =  day + "-" + (montth) + "-" + yeaer;

                List<SalesLineModel> orderModels=orderLines.selectpurchase(Date);


                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.phrecyclerview);
                SaleshistoryAdapter adapter = new SaleshistoryAdapter(orderModels, Purchasehistory.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);


            }
        });
    }

    @Override
    public void onclick(Object data) {

    }

    @Override
    public void onQtyclick(int qty, String price) {

    }
}

