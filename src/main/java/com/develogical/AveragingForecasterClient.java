package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import java.util.List;

public class AveragingForecasterClient implements ForecasterClient{

    private final List<ForecasterClient> delegates;

    public AveragingForecasterClient(List<ForecasterClient> delegates) {
        this.delegates = delegates;
    }

    @Override
    public Forecast forecastFor(Region region, Day day) {
        int total = 0;
        String summary = "unknown";
        for (ForecasterClient delegate : delegates) {
            Forecast forecast = delegate.forecastFor(region, day);
            total += forecast.temperature();
            summary = forecast.summary();
        }
        return new Forecast(summary, total);
    }
}
