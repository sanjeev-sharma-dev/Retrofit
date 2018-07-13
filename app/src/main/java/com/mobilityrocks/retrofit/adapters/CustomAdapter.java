package com.mobilityrocks.retrofit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobilityrocks.retrofit.pojos.Model;
import com.mobilityrocks.retrofit.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Model> list=new ArrayList<>();
    Context context;

    public CustomAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.screenSize.setText(list.get(position).getScreenSize());
        holder.backCamera.setText(list.get(position).getBackCamera());
        holder.rom.setText(list.get(position).getRom());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView backCamera,rom,screenSize;

        public MyViewHolder(View itemView) {
            super(itemView);

            backCamera=itemView.findViewById(R.id.backCamera);
            rom= itemView.findViewById(R.id.rom);
            screenSize=itemView.findViewById(R.id.screenSize);


        }
    }

    public void updateList(List<Model> list){
        this.list=list;
        notifyDataSetChanged();
    }


}
