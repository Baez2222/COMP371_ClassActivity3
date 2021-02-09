package com.example.android.classactivity3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{


    private List<Weather> weatherItems;

    // pass this list into the constructor of the adapter
    public WeatherAdapter(List<Weather> weatherItems) {
        this.weatherItems = weatherItems;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the custom layout
        View weatherItemView = inflater.inflate(R.layout.item_weather, parent, false);
        // return a new ViewHolder
        ViewHolder viewHolder = new ViewHolder(weatherItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // grab the actual data model (aka Weather object) based on the position
        Weather weatherItem = weatherItems.get(position);

        // set the view based on the data and the view names
        holder.textView_date.setText(weatherItem.getDate());
        holder.textView_description.setText(weatherItem.getDescription());
        holder.textView_feelsNumber.setText(weatherItem.getFeelsNumber() + "F");
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        // all the views you want to set as you render the row
        // date, description, feels like

        TextView textView_date;
        TextView textView_description;
        TextView textView_feelsNumber;

        // create constructor to set these

        public ViewHolder (View itemView){
            // itemView -> represents the entire view of each row
            super(itemView);
            // look up each view from the custom layout
            textView_date = itemView.findViewById(R.id.textView_date);
            textView_description = itemView.findViewById(R.id.textView_description);
            textView_feelsNumber = itemView.findViewById(R.id.textView_feelsNumber);
        }

    }

}
