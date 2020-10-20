package com.example.mobvansale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import java.math.BigDecimal;
import java.util.List;


public class ListofrouteAdapter extends RecyclerView.Adapter<ListofrouteAdapter.ViewHolder>{
    List<Routeinsertionmodel> listdata;
    OncompletCallback oncompletCallback;

    // RecyclerView recyclerView;
    public ListofrouteAdapter(List<Routeinsertionmodel> listdata,OncompletCallback oncompletCallback) {
        this.listdata = listdata;
        this.oncompletCallback=oncompletCallback;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.listofroute, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == 0) {
            holder.rname.setText("Route Name");
            holder.landmark.setText("Landmark");



        } else {
            final Routeinsertionmodel myListData = listdata.get(position);

            holder.rname.setText(myListData.getRname());
            holder.landmark.setText(myListData.getLandmark());

            //holder.imageView.setImageResource(listdata[position].getImgId());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    oncompletCallback.onclick(myListData);
                }
            });
        }

    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rname,landmark;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.rname = (TextView) itemView.findViewById(R.id.routename);
            this.landmark = (TextView) itemView.findViewById(R.id.landmark);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
