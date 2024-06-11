package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView item1, item2, item3, select;

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;

    boolean showAll = true; // Default to show all messages
    boolean showRead = true; // Default to show read messages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);

        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);

        select = findViewById(R.id.select);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        // Add sample data with read status
        DataClass data1 = new DataClass("User1", "has sent you a reminder", "2 hours ago");
        data1.setRead(false); // Set as unread
        dataList.add(data1);

        DataClass data2 = new DataClass("User2", "has sent you a reminder", "1 day ago");
        data2.setRead(true); // Set as read
        dataList.add(data2);

        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        int targetX = view.getLeft(); // Get the left position of the clicked tab
        select.animate().x(targetX).setDuration(100);
        resetTabColors((TextView) view);

        // Determine which tab is selected and update the message list accordingly
        if (viewId == R.id.item1) {
            showAll = true;
            showRead = true;
        } else if (viewId == R.id.item2) {
            showAll = false;
            showRead = false;
        } else if (viewId == R.id.item3) {
            showAll = false;
            showRead = true;
        }
        adapter.filterList(showAll, showRead);
    }

    private void resetTabColors(TextView selectedTab) {
        item1.setTextColor(getResources().getColor(R.color.BabyBlue));
        item2.setTextColor(getResources().getColor(R.color.BabyBlue));
        item3.setTextColor(getResources().getColor(R.color.BabyBlue));

        // Set the text color of the selected tab to white
        selectedTab.setTextColor(getResources().getColor(android.R.color.white));
    }
}
