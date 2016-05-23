
package com.seven.demobox.designpatterns.weatherInfo;

import android.os.Parcel;
import android.os.Parcelable;

public class Weather implements Parcelable{

    public String imageUrl;

    public Condition condition = new Condition();
    public Wind wind = new Wind();
    public Atmosphere atmosphere = new Atmosphere();
    public Forecast forecast = new Forecast();
    public Location location = new Location();
    public Astronomy astronomy = new Astronomy();
    public Units units = new Units();

    public String lastUpdate;

//    public Weather(Parcel source) {
//        condition.code = source.readInt();
//        condition.temp = source.readInt();
//        condition.dotCode = source.readInt();
//    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {

        @Override
        public Weather createFromParcel(Parcel source) {
            Weather weather = new Weather();
            weather.condition.code = source.readInt();
            weather.condition.temp = source.readInt();
            weather.condition.dotCode = source.readInt();
            return weather;
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public class Condition {
        public String description;
        public int code;
        public String date;
        public int temp;
        //add by seven
        public int dotCode;
    }

    public class Forecast {
        public int tempMin;
        public int tempMax;
        public String description;
        public int code;
    }

    public static class Atmosphere {
        public int humidity;
        public float visibility;
        public float pressure;
        public int rising;
    }

    public class Wind {
        public int chill;
        public int direction;
        public int speed;
    }

    public class Units {
        public String speed;
        public String distance;
        public String pressure;
        public String temperature;
    }

    public class Location {
        public String name;
        public String region;
        public String country;
    }

    public class Astronomy {
        public String sunRise;
        public String sunSet;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeInt(condition.code);
        dest.writeInt(condition.temp);
        dest.writeInt(condition.dotCode);
    }

}
