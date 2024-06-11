package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;
    private List<DataClass> filteredList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.filteredList = new ArrayList<>(dataList); // Initialize filtered list with all data
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass data = filteredList.get(position);
        holder.username.setText(data.getUserName());
        holder.description.setText(data.getDescription());
        holder.time.setText(data.getTime());

        // Assuming ignoreButton and checkButton are defined in the recycler_item layout
        // You can adjust this according to your actual layout
        holder.ignoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Ignore button click
            }
        });

        holder.checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Check button click
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    // Define MyViewHolder class within MyAdapter
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, description, time;
        Button ignoreButton, checkButton;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.time);
            ignoreButton = itemView.findViewById(R.id.ignoreButton);
            checkButton = itemView.findViewById(R.id.checkButton);
        }
    }

    // Method to filter the list based on read status
    public void filterList(boolean showAll, boolean showRead) {
        filteredList.clear();
        for (DataClass data : dataList) {
            if (showAll || (showRead && data.isRead()) || (!showRead && !data.isRead())) {
                filteredList.add(data);
            }
        }
        notifyDataSetChanged();
    }
}
