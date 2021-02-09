package com.example.android.classactivity3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private TextView title;
    private RecyclerView recyclerView;
    private ArrayList<Weather> weatherItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        if(intent.getStringExtra("none") == null){
            setContentView(R.layout.activity_second);

            title = findViewById(R.id.textView_title);
            title.setText(intent.getStringExtra("city") + ", " + intent.getStringExtra("country"));

            // look up the recycler view in the main activity xml
            recyclerView = findViewById(R.id.recyclerView_weatherItems);
            // create a new ArrayList
            weatherItems = new ArrayList<>();

            for(int i = 0; i < intent.getIntExtra("count", 0); i++){
                String description = intent.getStringExtra("weather_description"+i);
                String date = intent.getStringExtra("date"+i);
                Double feels_like = Double.valueOf(intent.getStringExtra("feels_like"+i));

                Weather weatherItem = new Weather(date, description, feels_like);
                // add it to the array listS
                weatherItems.add(weatherItem);
            }

            // create weather adapter to pass in the data
            WeatherAdapter adapter = new WeatherAdapter(weatherItems);
            // attach the adapter to the recycler view to populate
            recyclerView.setAdapter(adapter);
            // define where to add the layout manager
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else{
            setContentView(R.layout.activity_error);
        }
    }
}
