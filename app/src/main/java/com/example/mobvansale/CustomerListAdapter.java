package com.example.mobvansale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder>{
    List<Productinsertionmodel> listdata;
    OncompletCallback oncompletCallback;

    // RecyclerView recyclerView;
    public CustomerListAdapter(List<Productinsertionmodel> listdata,OncompletCallback oncompletCallback) {
        this.listdata = listdata;
        this.oncompletCallback=oncompletCallback;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Productinsertionmodel myListData = listdata.get(position);
        holder.textView.setText(myListData.getPname());
        //holder.imageView.setImageResource(listdata[position].getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                oncompletCallback.onclick(myListData);

                Toast.makeText(view.getContext(),"click on item: "+myListData.getPname(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
