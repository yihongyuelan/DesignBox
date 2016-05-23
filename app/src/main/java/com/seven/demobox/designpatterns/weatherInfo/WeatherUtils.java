package com.seven.demobox.designpatterns.weatherInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class WeatherUtils {
    public static void convertWeatherInfo(Weather weather) {
        weather.condition.dotCode = convertWeatherCode(weather.condition.code);
    }
    
    /* change the YAHOO weather code to HTC weather code */
    public static int convertWeatherCode(int code) {
        int dotCode = 0;
        switch (code) {
            case 32:
            case 34:
                //sunny
                dotCode = 1;
                break;
            case 27:
            case 31:
            case 33:
                //clear
                dotCode = 2;
                break;
            case 3:
            case 4:
            case 45:
            case 47:
                //thunderstorm
                dotCode = 3;
                break;
            case 19:
            case 21:
            case 22:
            case 25:
            case 26:
            case 28:
            case 44:
                //cloudy
                dotCode = 4;
                break;
            case 30:
                //party_cloudy_day
                dotCode = 5;
                break;
            case 29:
                //party_cloudy_night
                dotCode = 6;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 17:
            case 35:
            case 37:
            case 38:
            case 39:
            case 40:
                //rain
                dotCode = 7;
                break;
            case 5:
            case 6:
            case 7:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 41:
            case 42:
            case 43:
            case 46:
                //snow
                dotCode = 8;
                break;
            case 0:
            case 1:
            case 2:
            case 23:
            case 24:
                //windy
                dotCode = 9;
                break;
            case 20:
                //fog
                dotCode = 10;
                break;
            case 36:
                //hot
                dotCode = 11;
                break;
            default:
                dotCode = 0;
                break;
        }
        return dotCode;
    }
    
    public static boolean isTemperatureCelsius(Context context) {
        return "c".equals(getTemperatureUnit(context));
    }
    
    public static String getTemperatureUnit(Context context) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final String  tempUnit = prefs.getString("temp_unit", "c");
        Log.i("Seven", "tempUnit = " + tempUnit);
        return tempUnit;
    }
}
