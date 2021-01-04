package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.matteobattilana.weather.PrecipType;
import com.github.matteobattilana.weather.WeatherView;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText editText;
    Button button, more_info;
    ImageView imageView;
    TextView country_view, city_view, temperature_view,
            pressure_result, humidity_result, wind_speed_result, feels_like, current_weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("cash", MODE_PRIVATE);

        setContentView(R.layout.activity_main);


        editText = findViewById(R.id.city_name);
        button = findViewById(R.id.search_btn);
        more_info = findViewById(R.id.more_info_btn);
        country_view = findViewById(R.id.country_view);
        city_view = findViewById(R.id.city_view);
        temperature_view = findViewById(R.id.temperature_view);
        imageView = findViewById(R.id.imageView);
        pressure_result = findViewById(R.id.pressure_result);
        wind_speed_result = findViewById(R.id.wind_speed_result);
        humidity_result = findViewById(R.id.humidity_result);
        feels_like = findViewById(R.id.feels_like);
        current_weather = findViewById(R.id.current_weather);
        String saved = sharedPreferences.getString("city", null);



        if(saved != null){
          editText.setText(saved);
           findWeather(saved);


        }



        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });


        WeatherView weatherView = findViewById(R.id.weather_view);
        weatherView.setWeatherData(PrecipType.RAIN);

        


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editText.getText().toString().trim();
                try{
                    for( int i = 0; i < city.length(); i++){
                        if (city.charAt(i) == '1'){

                        }else if (city.charAt(i) == '2'){

                        }else if (city.charAt(i) == '3'){

                        }else if (city.charAt(i) == '4'){

                        }else if (city.charAt(i) == '5'){

                        }else if (city.charAt(i) == '6'){

                        }else if (city.charAt(i) == '7'){

                        }else if (city.charAt(i) == '8'){

                        }else if (city.charAt(i) == '9'){


                        }else{
                            findWeather(city);
                        }

                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Incorrect city, please check your input!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        }

    private void openActivity2() {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }


    public void findWeather(String s) {
            String city = s;
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=4de885008278fef50e68a476238a8ae2&units=metric";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //API call

                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        //Country searching

                        JSONObject object1 = jsonObject.getJSONObject("sys");
                        String country_location = object1.getString("country");
                        country_view.setText(country_location);


                        //City searching
                        String city_location = jsonObject.getString("name");
                        city_view.setText(city_location);


                        //Temperature searching
                        JSONObject object = jsonObject.getJSONObject("main");
                        double temp_location = object.getDouble("temp");
                        temperature_view.setText(temp_location + "°C");



                        //Weather Icon
                        JSONArray jsonArray = jsonObject.getJSONArray("weather");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        String img = jsonObject1.getString("icon");
                        Picasso.get().load("https://openweathermap.org/img/wn/" + img + "@2x.png").into(imageView);





                        //Pressure

                        JSONObject object4 = jsonObject.getJSONObject("main");
                        double pressure = object4.getDouble("pressure");
                        pressure_result.setText("Pressure: " + pressure + " hPa");

                        //Humidity

                        JSONObject object5 = jsonObject.getJSONObject("main");
                        double humidity = object5.getDouble("humidity");
                        humidity_result.setText("Humidity: " +humidity + " %");


                        //Wind Speed

                        JSONObject object6 = jsonObject.getJSONObject("wind");
                        double speed = object6.getDouble("speed");
                        wind_speed_result.setText("Wind speed: " + speed + " km/h");


                        //Feels like

                        JSONObject object9 = jsonObject.getJSONObject("main");
                        double f_like = object9.getDouble("feels_like");
                        feels_like.setText("Feels like: " + f_like + " °C" );



                        //Current Weather


                        JSONArray jsonArray1 = jsonObject.getJSONArray("weather");
                        String current_weather1 = jsonObject1.getString("main");
                        current_weather.setText(current_weather1);






                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);







    }




    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String city = editText.getText().toString();
        editor.putString("city", city);
        editor.apply();
    }
}