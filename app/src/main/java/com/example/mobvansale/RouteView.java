package com.example.mobvansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RouteView extends AppCompatActivity implements OncompletCallback  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_view);


        List<String> categories = new ArrayList<String>();
        Routeinsertion routeinsertion = new Routeinsertion(this);
        List<Routeinsertionmodel> routeinsertionmodels=routeinsertion.selectAllRoutes();

        for (Routeinsertionmodel routeinsertionmodel:routeinsertionmodels){
            categories.add(routeinsertionmodel.getRname());
            categories.add(routeinsertionmodel.getLandmark());



        }




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.Rrecyclerview);
        ListofrouteAdapter adapter = new ListofrouteAdapter(routeinsertionmodels,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onclick(Object data) {
        Routeinsertionmodel routeinsertionmodel=(Routeinsertionmodel) data;
        Intent intent=new Intent(this, Homeowner.class);

        startActivity(intent);
    }

    @Override
    public void onQtyclick(int qty, String price) {

    }
}
