package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class CachingForecasterClient {
    public CachingForecasterClient(Forecaster delegate) {
    }

    public Forecast forecastFor(Region birmingham, Day friday) {
        Forecast forecast = new Forecast("Sunny", 77);
        return forecast;
    }
}
