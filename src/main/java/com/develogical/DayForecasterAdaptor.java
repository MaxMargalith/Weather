package com.develogical;

import com.oocode.weather.DayForecaster;
import com.oocode.weather.Forecaster;
import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import static com.oocode.weather.Forecaster.Regions.*;

public class DayForecasterAdaptor implements ForecasterClient{

    private DayForecaster forecaster;
    public DayForecasterAdaptor() {
        this.forecaster = new DayForecaster();
    }

    @Override
    public Forecast forecastFor(Region region, Day day) {
        int dayInt = mapDayToInt(day);
        Forecaster.Regions regions = mapRegionToRegions(region);
        double forecastTemperature = this.forecaster.forecast(regions, dayInt);
        String forecastSummary = this.forecaster.forecastSummary(regions, dayInt);
        return new Forecast(forecastSummary, (int)forecastTemperature);
    }

    private Forecaster.Regions mapRegionToRegions(Region region) {
        switch (region) {
            case BIRMINGHAM:
                return MID_ENGLAND;
            case EDINBURGH:
                return MID_SCOTLAND;
            case GLASGOW:
                return MID_SCOTLAND;
            case LONDON:
                return SE_ENGLAND;
            case MANCHESTER:
                return MID_ENGLAND;
            case NORTH_ENGLAND:
                return NE_ENGLAND; // average with NW?
            case SOUTH_WEST_ENGLAND:
                return SW_ENGLAND;
            case SOUTH_EAST_ENGLAND:
                return SE_ENGLAND;
            case WALES:
                return N_WALES; // average with S?
            default :
                return null;
        }
    }

    private int mapDayToInt(Day day) {
        int dayInt = 0;
        if (day == Day.MONDAY) {
            dayInt = 1;
        }
        else if (day == Day.TUESDAY) {
            dayInt = 2;
        }
        else if (day == Day.WEDNESDAY) {
            dayInt = 3;
        }
        else if (day == Day.THURSDAY) {
            dayInt = 4;
        }
        else if (day == Day.FRIDAY) {
            dayInt = 5;
        }
        else if (day == Day.SATURDAY) {
            dayInt = 6;
        }
        else if (day == Day.SUNDAY) {
            dayInt = 7;
        }
        return dayInt;
        }
}
