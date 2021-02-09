package com.example.android.classactivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button button_go;
    private TextView textView_city;

    private static String base_url = "https://api.openweathermap.org/data/2.5/forecast?q=";
    private static String end_url = "&units=imperial&exclude=daily,minutely&appid=926d2695af7d14b40b21dea07080d5bd";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_city = findViewById(R.id.textView_city);
        button_go = findViewById(R.id.button_go);
        // add click listener for button_go
        button_go.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                launchNextActivity(view);
            }
        });
    }

    public void launchNextActivity(View view){
        String city_name = textView_city.getText().toString();

        String api_url = base_url + city_name + end_url;

        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // when you get a 200 status code
                Log.d("api response", new String(responseBody));
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    // add weather data into the intent

                    // add city and country
                    JSONObject city = json.getJSONObject("city");
                    intent.putExtra("city", city.getString("name"));
                    intent.putExtra("country", city.getString("country"));


                    // add time, feels like and weather descr for each
                    JSONArray listArray = json.getJSONArray("list");
                    //Log.println(Log.INFO, "JSON array", listArray.getJSONObject(0).toString());
                    for (int i = 0; i < Integer.parseInt(json.getString("cnt")); i++){
                        // weather descr
                        intent.putExtra("weather_description"+i, listArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                        //Log.println(Log.INFO, "weather descr", listArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                        // date and time
                        intent.putExtra("date"+i, listArray.getJSONObject(i).getString("dt_txt"));
                        //Log.println(Log.INFO, "weather descr", listArray.getJSONObject(i).getString("dt_txt"));
                        // feels like
                        intent.putExtra("feels_like"+i, listArray.getJSONObject(i).getJSONObject("main").getString("feels_like"));
                        //Log.println(Log.INFO, "weather descr", listArray.getJSONObject(i).getJSONObject("main").getString("feels_like"));
                    }

                    // convert any json data into a string to put into the intent
                    // when you receive the intent in the next activity,
                    // convert it back to the json data
                    intent.putExtra("count", Integer.parseInt(json.getString("cnt")));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String(responseBody));

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // add to intent no such city exists in API calls
                intent.putExtra("none", "none");
                startActivity(intent);
            }
        });
    }

}