package com.example.weatherapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;

   private ArrayList<String> temperature;
    private ArrayList<String> humidity;
    private ArrayList<String> condition;
    private ArrayList<String> icon;
    private ArrayList<String> dates;


    Adapter(Context context, ArrayList<String> temperature, ArrayList<String> humidity, ArrayList<String> condition, ArrayList<String> icon, ArrayList<String> dates){
        this.layoutInflater = LayoutInflater.from(context);
       this.temperature = temperature;
       this.humidity = humidity;
       this.dates = dates;
       this.condition = condition;
       this.icon = icon;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.custom_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.future_temperature.setText(temperature.get(i) + "°C");
        viewHolder.future_date.setText(dates.get(i));
        viewHolder.future_weather.setText(condition.get(i));
        viewHolder.future_humidity.setText("Humidity: " + humidity.get(i) + "%");
        Picasso.get().load("https://openweathermap.org/img/wn/" + icon.get(i) + "@2x.png").into(viewHolder.imageView2);

    }



    @Override
    public int getItemCount() {
        return temperature.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView future_date, future_temperature, future_humidity, future_weather;
        ImageView imageView2;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            future_date = itemView.findViewById(R.id.future_date);
            future_temperature = itemView.findViewById(R.id.future_temperature);
            future_humidity = itemView.findViewById(R.id.future_humidity);
            future_weather = itemView.findViewById(R.id.future_weather);
            imageView2 = itemView.findViewById(R.id.imageView2);





        }
    }
}
