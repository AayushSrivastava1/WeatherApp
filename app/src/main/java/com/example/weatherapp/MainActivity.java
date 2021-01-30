package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{
    EditText enterLatitude;
    EditText enterLongitude;
    TextView nameshow1;
    TextView nameshow2;
    TextView nameshow3;
    TextView tempshow1;
    TextView tempshow2;
    TextView tempshow3;
    ImageView tempimageshow1;
    ImageView tempimageshow2;
    ImageView tempimageshow3;
    TextView timeshow1;
    TextView timeshow2;
    TextView timeshow3;
    TextView Dateshow1;
    TextView Dateshow2;
    TextView Dateshow3;
    TextView tempdescshow1;
    TextView tempdescshow2;
    TextView tempdescshow3;
    TextView text1;
    Button search;
    String url;
    Switch mySwitch;

    class getWeather extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL (urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                while((line = reader.readLine())!=null)
                {
                    result.append(line).append("\n");
                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                String j = "";
                j = jsonObject.getJSONArray("list").getJSONObject(0).getString("name");
                nameshow1.setText("Location Name: "+j);
                j = jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp");
                tempshow1.setText("Temperature: "+j+"°F");
                j = jsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("icon");
                tempimageshow1.setImageResource(imageResourcesetter(j));
                j = jsonObject.getJSONArray("list").getJSONObject(0).getString("dt");
                timeshow1.setText("Time: "+unixtodt(j));
                j = jsonObject.getJSONArray("list").getJSONObject(0).getString("dt");
                Dateshow1.setText("Date: "+unixtodt2(j));
                j = jsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");
                tempdescshow1.setText("Description Of Weather: "+j);


                j = jsonObject.getJSONArray("list").getJSONObject(1).getString("name");
                nameshow2.setText("Location Name: "+j);
                j = jsonObject.getJSONArray("list").getJSONObject(1).getJSONObject("main").getString("temp");
                tempshow2.setText("Temperature: "+j+"°F");
                j = jsonObject.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("icon");
                tempimageshow2.setImageResource(imageResourcesetter(j));
                j = jsonObject.getJSONArray("list").getJSONObject(1).getString("dt");
                timeshow2.setText("Time: "+unixtodt(j));
                j = jsonObject.getJSONArray("list").getJSONObject(1).getString("dt");
                Dateshow2.setText("Date: "+unixtodt2(j));
                j = jsonObject.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("main");
                tempdescshow2.setText("Description Of Weather: "+j);


                j = jsonObject.getJSONArray("list").getJSONObject(2).getString("name");
                nameshow3.setText("Location Name: "+j);
                j = jsonObject.getJSONArray("list").getJSONObject(2).getJSONObject("main").getString("temp");
                tempshow3.setText("Temperature: "+j+"°F");
                j = jsonObject.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("icon");
                tempimageshow3.setImageResource(imageResourcesetter(j));
                j = jsonObject.getJSONArray("list").getJSONObject(2).getString("dt");
                timeshow3.setText("Time: "+unixtodt(j));
                j = jsonObject.getJSONArray("list").getJSONObject(2).getString("dt");
                Dateshow3.setText("Date: "+unixtodt2(j));
                j = jsonObject.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("main");
                tempdescshow3.setText("Description Of Weather: "+j);

                mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean b)
                    {
                        if(b)
                        {
                            try {

                                String u = jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp");;
                                double k = Double.parseDouble(u);
                                double e = (k - 32)*5/9;
                                e = Math.round(e);
                                u = Double.toString(e);
                                tempshow1.setText("Temp: " + u + "°C");

                                 u = jsonObject.getJSONArray("list").getJSONObject(1).getJSONObject("main").getString("temp");;
                                 k = Double.parseDouble(u);
                                e = (k - 32)*5/9;
                                e = Math.round(e);
                                u = Double.toString(e);
                                tempshow2.setText("Temp: " + u + "°C");

                                u = jsonObject.getJSONArray("list").getJSONObject(2).getJSONObject("main").getString("temp");;
                                 k = Double.parseDouble(u);
                                 e = (k - 32)*5/9;
                                e = Math.round(e);
                                u = Double.toString(e);
                                tempshow3.setText("Temp: " + u + "°C");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mySwitch.setText("°C");
                        }
                        if(!b)
                        {
                            try {
                                String u = jsonObject.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp");
                                tempshow1.setText("Temperature: "+u+"°F");
                                u = jsonObject.getJSONArray("list").getJSONObject(1).getJSONObject("main").getString("temp");
                                tempshow2.setText("Temperature: "+u+"°F");
                                u = jsonObject.getJSONArray("list").getJSONObject(2).getJSONObject("main").getString("temp");
                                tempshow3.setText("Temperature: "+u+"°F");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mySwitch.setText("°F");
                        }
                    }
                });



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterLatitude = findViewById(R.id.EnterLatitude);
        enterLongitude = findViewById(R.id.EnterLongitude);
        search = findViewById(R.id.SearchButton);
        nameshow1 = findViewById(R.id.CityNameShower1);
        nameshow2 = findViewById(R.id.CityNameShower2);
        nameshow3 = findViewById(R.id.CityNameShower3);
        tempshow1 = findViewById(R.id.CityTemperature1);
        tempshow2 = findViewById(R.id.CityTemperature2);
        tempshow3 = findViewById(R.id.CityTemperature3);
        tempimageshow1 = findViewById(R.id.CityTemperatureImage1);
        tempimageshow2 = findViewById(R.id.CityTemperatureImage2);
        tempimageshow3 = findViewById(R.id.CityTemperatureImage3);
        timeshow1 = findViewById(R.id.CityTimeShower1);
        timeshow2 = findViewById(R.id.CityTimeShower2);
        timeshow3 = findViewById(R.id.CityTimeShower3);
        Dateshow1 = findViewById(R.id.CityDateShower1);
        Dateshow2 = findViewById(R.id.CityDateShower2);
        Dateshow3 = findViewById(R.id.CityDateShower3);
        tempdescshow1 = findViewById(R.id.CityTempDescShower1);
        tempdescshow2 = findViewById(R.id.CityTempDescShower2);
        tempdescshow3 = findViewById(R.id.CityTempDescShower3);
        mySwitch = findViewById(R.id.tempSwitch);
        text1 = findViewById(R.id.tempTextView);


        final String[] temp = {""};

        search.setBackgroundColor(Color.YELLOW);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lat = enterLatitude.getText().toString();
                String lon = enterLongitude.getText().toString();
                try {
                    if (enterLatitude != null && enterLongitude != null)
                    {
                            url = "https://api.openweathermap.org/data/2.5/find?lat="+lat+"&lon="+lon+"&cnt=3&units=imperial&appid=ca20b5368143cc5f05fd104bd8681503";
                            text1.setText("");
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Enter City", Toast.LENGTH_SHORT).show();
                    }
                    getWeather task = new getWeather();
                    temp[0] = task.execute(url).get();
                } catch (ExecutionException e)
                {
                    e.printStackTrace();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if(temp[0] == null)
                {
                    text1.setText("Cannot Find Weather");
                }
            }
        });
    }
    public String unixtodt(String date)//Converts to time
    {
        Long l = Long.parseLong(date);
        Date date1 = new java.util.Date(l*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm a");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("EST"));
        String formattedDate = sdf.format(date1);
        return formattedDate+" EST";
    }
    public String unixtodt2(String date)//Converts to date
    {
        Long l = Long.parseLong(date);
        Date date1 = new java.util.Date(l*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = sdf.format(date1);
        return formattedDate;
    }

    public int imageResourcesetter(String h)
    {
        int z = 0;
        if(h.equalsIgnoreCase("01d"))
        {
            z = R.drawable.clearskypicday;
        }
        if(h.equalsIgnoreCase("01n"))
        {
            z = R.drawable.clearskypicnight;
        }
        if(h.equalsIgnoreCase("02d"))
        {
            z = R.drawable.fewcloudspicday;
        }
        if(h.equalsIgnoreCase("02n"))
        {
            z = R.drawable.fewcloudspicnight;
        }
        if(h.equalsIgnoreCase("03d")||h.equalsIgnoreCase("03n"))
        {
            z = R.drawable.scatteredcloudspic;
        }
        if(h.equalsIgnoreCase("04d")||h.equalsIgnoreCase("04n"))
        {
            z = R.drawable.brokencloudspic;
        }
        if(h.equalsIgnoreCase("09d")||h.equalsIgnoreCase("09n"))
        {
            z = R.drawable.showerrainpic;
        }
        if(h.equalsIgnoreCase("10d"))
        {
            z = R.drawable.rainpicday;
        }
        if(h.equalsIgnoreCase("10n"))
        {
            z = R.drawable.rainpicnight;
        }
        if(h.equalsIgnoreCase("11d")||h.equalsIgnoreCase("11n"))
        {
            z = R.drawable.thunderstormpic;
        }
        if(h.equalsIgnoreCase("13d")||h.equalsIgnoreCase("13n"))
        {
            z = R.drawable.snowpic;
        }
        if(h.equalsIgnoreCase("50d")||h.equalsIgnoreCase("50n"))
        {
            z = R.drawable.mistpic;
        }
        return z;
    }




}











