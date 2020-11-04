package com.example.mobvansale;

import android.graphics.Color;
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


public class listofvendororderAdapter extends RecyclerView.Adapter<listofvendororderAdapter.ViewHolder>{
    List<OrderModel> listdata;
    OncompletCallback oncompletCallback;

    // RecyclerView recyclerView;
    public listofvendororderAdapter(List<OrderModel> listdata,OncompletCallback oncompletCallback) {
        this.listdata = listdata;
        this.oncompletCallback=oncompletCallback;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_listofvendororder_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == 0) {
            holder.cname.setText("Vendor Name");
            holder.status.setText("Status");

            holder.itemView.setBackgroundColor(Color.parseColor("#03DAC5"));

        } else {
            final OrderModel myListData = listdata.get(position-1);

            holder.cname.setText(myListData.getCustomerName());
            holder.status.setText( myListData.getSTATUS());

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
        return listdata.size()+1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cname,status;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.cname = (TextView) itemView.findViewById(R.id.cname);
            this.status = (TextView) itemView.findViewById(R.id.status);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
