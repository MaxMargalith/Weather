package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingForecasterClient implements ForecasterClient {
    private final ForecasterClient forecaster;
    private final int cacheLimit;

    Map<String, Forecast> forecastCache;
    List<String> insertionOrder;

    public CachingForecasterClient(ForecasterClient delegate, int cacheLimit) {
        this.forecaster = delegate;
        this.cacheLimit = cacheLimit;
        this.forecastCache = new HashMap<>();
        this.insertionOrder = new ArrayList<>();
    }

    public Forecast forecastFor(Region region, Day day) {
        Forecast forecast = null;
        String key = generateKey(region,day);
        if(isInCache(key)){
            forecast = forecastCache.get(key);
        } else {
            forecast = forecaster.forecastFor(region, day);
            ifSizeLimitRemoveOldest();
            forecastCache.put(key, forecast);
            insertionOrder.add(key);
        }
        return forecast;
    }

    private void ifSizeLimitRemoveOldest() {
        if (this.forecastCache.size() == this.cacheLimit) {
            String oldestEntry = this.insertionOrder.get(0);
            this.forecastCache.remove(oldestEntry);
            this.insertionOrder.remove(0);
        }
    }

    private boolean isInCache(String key) {
        return forecastCache.containsKey(key);
    }

    private String generateKey(Region region, Day day){
        return region.toString()+"_"+day.toString();
    }
}
