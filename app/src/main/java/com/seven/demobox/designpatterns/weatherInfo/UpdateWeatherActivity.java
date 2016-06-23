package com.seven.demobox.designpatterns.weatherInfo;

import com.android.volley.RequestQueue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.goldsand.dotcover.CoverService;
//import com.goldsand.dotcover.GlobalApplication;
//import com.goldsand.dotcover.weather.YahooClient.WeatherClientListener;

public class UpdateWeatherActivity extends Activity {

    private TextView tv;
    private SharedPreferences prefs;
    private RequestQueue requestQueue;
    private Context mContext;
    private Button mPhoneButton;
    private Button mSmsButton;
    private Button mEmailButton;
    private ButtonClickListener listener;
    private TextView separatorTv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = UpdateWeatherActivity.this;
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        tv = new TextView(this);
        separatorTv = new TextView(this);
        separatorTv.setText("====Test for Notification switch====");
        listener = new ButtonClickListener();
        mPhoneButton = new Button(this);
        mPhoneButton.setText("MissCall broadcast");
        mPhoneButton.setOnClickListener(listener);
        mSmsButton = new Button(this);
        mSmsButton.setText("Message broadcast");
        mSmsButton.setOnClickListener(listener);
        mEmailButton = new Button(this);
        mEmailButton.setText("Email broadcast");
        mEmailButton.setOnClickListener(listener);
        layout.addView(tv);
        layout.addView(separatorTv);
        layout.addView(mPhoneButton);
        layout.addView(mSmsButton);
        layout.addView(mEmailButton);
        setContentView(layout);
        
//        YahooClient.request(mContext, this);
        
//            prefs = PreferenceManager.getDefaultSharedPreferences(this);
//            requestQueue = Volley.newRequestQueue(getApplicationContext());
//            refreshData();
    }

    class ButtonClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            if (v == mPhoneButton) {
                intent.setAction("com.seven.phone");
            }else if (v == mSmsButton) {
                intent.setAction("com.seven.sms");
            }else if (v == mEmailButton) {
                intent.setAction("com.seven.email");
            }
            sendBroadcast(intent);
        }
    }

    public void onWeatherResponse(Weather weather) {
        int code = weather.condition.code;
        int temp = weather.condition.temp;
        int dotcode = weather.condition.dotCode;
        Log.i("Seven", "seven code is:"+code+"\n"+"temp="+temp+"\n"+"dotcode="+dotcode);
        tv.setText("====== CURRENT ======" + "\n" +
                //"date: " + weatherInfo.getCurrentConditionDate() + "\n" +
                "code is: " + code + "\n" +
                "temp is: " + temp + "\n" +
                "dotcode is: " + dotcode
                );
    }

    private void refreshData() {

        if (prefs == null)
            return ;


        String woeid = prefs.getString("woeid", null);
        //PLog.d("SwA", "WOEID ["+woeid+"]");
        if (woeid != null) {
            String loc = prefs.getString("cityName", null) + "," + prefs.getString("country", null);
            String unit = prefs.getString("temp_unit", null);

            YahooClient.getWeather(woeid, unit, requestQueue, new YahooClient.WeatherClientListener() {
                @Override
                public void onWeatherResponse(Weather weather) {
                    //PLog.d("SwA", "Weather ["+weather+"] - Cond ["+weather.condition+"] - Temp ["+weather.condition.temp+"]");
                    int code = weather.condition.code;
                    
                    //description
                    String des = weather.condition.description;
                    //tvDescrWeather.setText(weather.condition.description);
                    
                    //location
                    String loc = weather.location.name + "," + weather.location.region + "," + weather.location.country;
                    //tvLocation.setText(weather.location.name + "," + weather.location.region + "," + weather.location.country);
                    
                    // Temperature data
                    String tem = "" + weather.condition.temp;
                    //tvTemperature.setText("" + weather.condition.temp);
                    Log.i("Seven", "code is:"+code+"\n"+"temp="+tem);

                    //int resId = getResource(weather.units.temperature, weather.condition.temp);
                    //( (TextView) findViewById(R.id.lineTxt)).setBackgroundResource(resId);

                    //tvTempUnit.setText(weather.units.temperature);
                    //tvTempMin.setText("" + weather.forecast.tempMin + " " + weather.units.temperature);
                    //tvTempMax.setText("" + weather.forecast.tempMax + " " + weather.units.temperature);

                    // Humidity data
                    String hum = "" + weather.atmosphere.humidity + " %";
                    //tvHum.setText("" + weather.atmosphere.humidity + " %");

                    // Wind data
                    String windSpeed = "" + weather.wind.speed + " " + weather.units.speed;
                    //tvWindSpeed.setText("" + weather.wind.speed + " " + weather.units.speed);
                    String windDirecion = "" + weather.wind.direction + "°";
                    //tvWindDeg.setText("" + weather.wind.direction + "°");

                    // Pressure data
                    String press = "" + weather.atmosphere.pressure + " " + weather.units.pressure;
                    //tvPress.setText("" + weather.atmosphere.pressure + " " + weather.units.pressure);
                    /*int status = weather.atmosphere.rising;
                    String iconStat = "";
                    switch (status) {
                        case 0 :
                            iconStat = "-";
                            break;
                        case 1 :
                            iconStat = "+";
                            break;
                        case 2 :
                            iconStat = "-";
                            break;
                    };
                    tvPressStatus.setText(iconStat);*/

                    // Visibility
                    String visibility = "" + weather.atmosphere.visibility + " " + weather.units.distance;
                    //tvVisibility.setText("" + weather.atmosphere.visibility + " " + weather.units.distance);

                    // Astronomy
                    //tvSunrise.setText(weather.astronomy.sunRise);
                    //tvSunset.setText(weather.astronomy.sunSet);

                    // Update

                    /*IWeatherImageProvider provider = new WeatherImageProvider();
                    provider.getImage(code, requestQueue, new IWeatherImageProvider.WeatherImageListener() {
                        @Override
                        public void onImageReady(Bitmap image) {
                            weatherImage.setImageBitmap(image);
                        }
                    });*/
                    
                  //seven-start
                    tv.setText("====== CURRENT ======" + "\n" +
                            //"date: " + weatherInfo.getCurrentConditionDate() + "\n" +
                            "location: " + loc + "\n" +
                            "weather: " + des + "\n" +
                            "temperature in ºC: " + ((int)((weather.condition.temp - 32) * 5 / 9)) + "\n" +
                            "temperature in ºF: " + tem + "\n" +
                            //"wind chill in ºF: " + weatherInfo.getWindChill() + "\n" +
                            "wind direction: " + windDirecion + "\n" +
                            "wind speed: " + windSpeed + "\n" +
                            "Humidity: " + hum + "\n" +
                            "Pressure: " + press + "\n" +
                            "Visibility: " + visibility
                            );
                    //seven-end
                }
            });


        }
    }
}
