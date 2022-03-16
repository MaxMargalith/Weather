package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CachingForecasterClientTest {
    private final Forecaster delegate = mock(Forecaster.class);
    private final CachingForecasterClient underTest = new CachingForecasterClient(delegate);

    @Test
    public void getForecastsFromDelegate() {
         when(delegate.forecastFor(Region.BIRMINGHAM, Day.FRIDAY))
                 .thenReturn(new Forecast("Sunny", 77));

         Forecast forecast = underTest.forecastFor(Region.BIRMINGHAM, Day.FRIDAY);
         assertThat(forecast.summary(), equalTo("Sunny"));
         assertThat(forecast.temperature(), equalTo(77));

    }
}
