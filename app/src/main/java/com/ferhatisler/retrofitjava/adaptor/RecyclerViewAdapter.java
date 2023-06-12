package com.ferhatisler.retrofitjava.adaptor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferhatisler.retrofitjava.R;
import com.ferhatisler.retrofitjava.model.CyroptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<CyroptoModel> cyrptoList ;
    private String[] colors = {"#1f3865","#e4c88e","#9F00FF","#C7BBC9","#90D2D8","#3A3A3A","#00B4FF","#BAA655"};

    public RecyclerViewAdapter(ArrayList<CyroptoModel> cyrptoList) {
        this.cyrptoList = cyrptoList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cyrptoList.get(position),colors,position);

    }

    @Override
    public int getItemCount() {
        return cyrptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName ;
        TextView textPrice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(CyroptoModel cyroptoModel , String[] colors , Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8 ]));

            textName = itemView.findViewById(R.id.text_name);
            textPrice = itemView.findViewById(R.id.text_price);
            textName.setText(cyroptoModel.currency);
            textPrice.setText(cyroptoModel.price);
        }

    }
}
