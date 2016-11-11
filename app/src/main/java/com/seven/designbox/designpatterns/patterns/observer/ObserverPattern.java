package com.seven.designbox.designpatterns.patterns.observer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.seven.designbox.R;
import com.seven.designbox.designpatterns.common.PatternsCommonActivity;
import com.seven.designbox.designpatterns.weatherInfo.CityResult;
import com.seven.designbox.designpatterns.weatherInfo.Weather;
import com.seven.designbox.designpatterns.weatherInfo.YahooClient;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern extends PatternsCommonActivity{

    private String mWoeid;
    private String mCityName;
    private String mTempUnit = "c";
    private TextView mWeatherTV;
    private static final int GET_WEATHER_INFO = 0;
    private static final int CURRENT_DISPLAY = 1;
    private static final int GENERNAL_DISPLAY = 2;
    private WeatherStation mStation;
    private WeatherData mData;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_WEATHER_INFO:
                    mWeatherTV.setText("Getting weather info...");
                    refreshData();
                    break;
                case CURRENT_DISPLAY:
                    mStation.setMeasurements(mData);
                    break;
                case GENERNAL_DISPLAY:
                    mStation.setMeasurements(mData);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.observer_pattern_title);
        setContentView(R.layout.cityfinder_layout);
        mWeatherTV = (TextView) findViewById(R.id.weatherInfo);
        AutoCompleteTextView edt = (AutoCompleteTextView) findViewById(R.id.cityEditor);
        edt.setAdapter(new CityAdapter(this, null));

        edt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityResult result = (CityResult) parent.getItemAtPosition(position);
                mWoeid = result.getWoeid();
                mCityName = result.getCityName();
                mHandler.sendMessage(mHandler.obtainMessage(GET_WEATHER_INFO));
            }
        });

        mStation = new WeatherStation();
        mData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        GeneralDisplay generalDisplay = new GeneralDisplay();
        //mStation.registerObserver(currentDisplay);
        mStation.addObserver(generalDisplay);

    }

    private class CityAdapter extends ArrayAdapter<CityResult> implements Filterable {

        private Context context;
        private List<CityResult> cityList = new ArrayList<CityResult>();

        public CityAdapter(Context context, List<CityResult> cityList) {
            super(context, R.layout.cityresult_layout, cityList);
            this.cityList = cityList;
            this.context = context;
        }

        @Override
        public CityResult getItem(int position) {
            if (cityList != null)
                return cityList.get(position);
            return null;
        }

        @Override
        public int getCount() {
            if (cityList != null)
                return cityList.size();
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View result = convertView;

            if (result == null) {
                LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                result = inf.inflate(R.layout.cityresult_layout, parent, false);
            }

            TextView tv = (TextView) result.findViewById(R.id.cityName);
            tv.setText(cityList.get(position).getCityName() + "," + cityList.get(position).getCountry());
            return result;
        }

        @Override
        public long getItemId(int position) {
            if (cityList != null)
                return cityList.get(position).hashCode();
            return 0;
        }

        @Override
        public Filter getFilter() {
            Filter cityFilter = new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    if (constraint == null || constraint.length() < 2)
                        return results;
                    List<CityResult> cityResultList = YahooClient.getCityList(constraint.toString());
                    results.values = cityResultList;
                    results.count = cityResultList.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    cityList = (List) results.values;
                    notifyDataSetChanged();
                }
            };
            return cityFilter;
        }
    }

    private void refreshData() {
        if (TextUtils.isEmpty(mWoeid) || TextUtils.isEmpty(mCityName) || TextUtils.isEmpty(mTempUnit))
            return;

        YahooClient.getWeather(mWoeid, mTempUnit, Volley.newRequestQueue(getApplicationContext()), new YahooClient.WeatherClientListener() {
            @Override
            public void onWeatherResponse(Weather weather) {
                int code = weather.condition.code;
                //description
                String des = weather.condition.description;

                //location
                String loc = weather.location.name + "," + weather.location.country;

                // Temperature data
                String tem = "" + weather.condition.temp;

                // Humidity data
                String hum = "" + weather.atmosphere.humidity + " %";

                // Wind data
                String windSpeed = "" + weather.wind.speed + " " + weather.units.speed;
                String windDirecion = "" + weather.wind.direction + "°";

                // Pressure data
                String press = "" + weather.atmosphere.pressure + " " + weather.units.pressure;
                // Visibility
                String visibility = "" + weather.atmosphere.visibility + " " + weather.units.distance;

                mWeatherTV.setText(
                                "====== CURRENT ======" + "\n" +
                                //"date: " + weatherInfo.getCurrentConditionDate() + "\n" +
                                "location: " + loc + "\n" +
                                "weather: " + des + "\n" +
                                // if unit is ºF then need to transform to ºC
                                //"temperature in ºC: " + ((int) ((weather.condition.temp - 32) * 5 / 9)) + "\n" +
                                "temperature in ºC: " + tem + " ºC" + "\n" +
                                //"wind chill in ºF: " + weatherInfo.getWindChill() + "\n" +
                                "wind direction: " + windDirecion + "\n" +
                                "wind speed: " + windSpeed + "\n" +
                                "Humidity: " + hum + "\n" +
                                "Pressure: " + press + "\n" +
                                "Visibility: " + visibility
                );
                mData.temperature = tem;
                mData.pressure = press;
                mData.humidity = hum;
                //mHandler.sendMessage(mHandler.obtainMessage(CURRENT_DISPLAY));
                mHandler.sendMessage(mHandler.obtainMessage(GENERNAL_DISPLAY));
            }
        });
    }
}
