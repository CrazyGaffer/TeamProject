package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Activity2 extends MainActivity {
    private SlidrInterface slidr;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> temperature;
    ArrayList<String> humidity;
    ArrayList<String> condition;
    ArrayList<String> icon;
    TextView future_date, future_temperature;
    SharedPreferences sharedPreferences;
    ArrayList<String> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("cash", MODE_PRIVATE);
        dates = new ArrayList<>();
        temperature = new ArrayList<>();
        humidity = new ArrayList<>();
        icon = new ArrayList<>();
        condition = new ArrayList<>();
        setContentView(R.layout.activity_2);
        slidr = Slidr.attach(this);
        future_date = findViewById(R.id.future_date);
        future_temperature = findViewById(R.id.future_temperature);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String saved = sharedPreferences.getString("city", null);
        if (saved != null) {
            findWeather2(saved);




        }


    }

    public void findWeather2(String saved){
        String city = saved;
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + saved +"&cnt=12&appid=4de885008278fef50e68a476238a8ae2&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Calling API
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    //Date location

                    JSONArray object1 = jsonObject.getJSONArray("list");

                    for (int i = 0; i<12 ;i++){
                        JSONObject slovo = object1.getJSONObject(i);
                       JSONObject slovo2 = slovo.getJSONObject("main");
                       JSONArray slovo3 = slovo.getJSONArray("weather");
                       JSONObject slovo4 = slovo3.getJSONObject(0);



                        dates.add(slovo.getString("dt_txt"));
                        temperature.add(slovo2.getString("temp"));
                        humidity.add(slovo2.getString("humidity"));
                        condition.add(slovo4.getString("main"));
                        icon.add(slovo4.getString("icon"));

                    }




                    adapter = new Adapter(Activity2.this,temperature,humidity,condition,icon,dates);
                    recyclerView.setAdapter(adapter);








                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(Activity2.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Activity2.this);
        requestQueue.add(stringRequest);
    }

}

