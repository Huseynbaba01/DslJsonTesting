package com.example.dsljsontesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private List<Data> list;
    private Context context;

    public MyAdapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        RecyclerView.ViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).itemName.setText(list.get(position).getItemName());
        ((MyViewHolder) holder).itemCode.setText(list.get(position).getItemCode());
        ((MyViewHolder) holder).unitCode.setText(list.get(position).getUnitCode());
        ((MyViewHolder) holder).quantity.setText(String.valueOf(list.get(position).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView itemName;
        public TextView itemCode;
        public TextView unitCode;
        public TextView quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemName = itemView.findViewById(R.id.item_name);
            this.itemCode = itemView.findViewById(R.id.item_code);
            this.unitCode = itemView.findViewById(R.id.unit_code);
            this.quantity = itemView.findViewById(R.id.quantity);

        }
    }
}
