package com.example.johney.recyclerviewdragtest;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnStartDragListener{

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecylerView();
    }

    private void initRecylerView() {

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getTestCityNames(), this);


        CityNameItemTouchHelperCallback callback = new CityNameItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);
    }

    private List<String> getTestCityNames() {
        List<String> cityNames = new ArrayList<>();
        cityNames.add("Seoul");
        cityNames.add("Tokyo");
        cityNames.add("New York");
        cityNames.add("London");
        cityNames.add("Busan");
        cityNames.add("Beijing");

        return cityNames;
    }

    @Override
    public void onStartDrag(RecyclerViewAdapter.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
