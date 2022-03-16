package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import java.util.HashMap;
import java.util.Map;

public class CachingForecasterClient {
    private final Forecaster forecaster;

    Map<String, Forecast> forecastCache;
    public CachingForecasterClient(Forecaster delegate) {
        this.forecaster = delegate;
        forecastCache = new HashMap<String, Forecast>();
    }

    public Forecast forecastFor(Region region, Day day) {
        Forecast forecast = null;
        String key = generateKey(region,day);
        if(isInCache(key)){
            forecast = forecastCache.get(key);

        } else {
            forecast = forecaster.forecastFor(region, day);
            forecastCache.put(key, forecast);
        }
        return forecast;
    }

    private boolean isInCache(String key) {

        return forecastCache.containsKey(key);
    }

    private String generateKey(Region region, Day day){

        return region.toString()+"_"+day.toString();
    }
}
