package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class ForecasterAdaptor implements ForecasterClient {

    private Forecaster forecaster;
    public ForecasterAdaptor() {
        this.forecaster = new Forecaster();
    }

    @Override
    public Forecast forecastFor(Region region, Day day) {
        Forecast forecast = this.forecaster.forecastFor(region, day);
        return forecast;
    }
}
