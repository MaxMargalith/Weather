package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

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

        when(delegate.forecastFor(Region.LONDON, Day.FRIDAY))
                .thenReturn(new Forecast("Cloudy", 50));

        Forecast forecast2 = underTest.forecastFor(Region.LONDON, Day.FRIDAY);
        assertThat(forecast2.summary(), equalTo("Cloudy"));
        assertThat(forecast2.temperature(), equalTo(50));
    }

    @Test
    public void checkIfCacheIsUsed() {
        when(delegate.forecastFor(Region.BIRMINGHAM, Day.FRIDAY))
                .thenReturn(new Forecast("Sunny", 77));

        Forecast forecast1 = underTest.forecastFor(Region.BIRMINGHAM, Day.FRIDAY);
        Forecast forecast2 = underTest.forecastFor(Region.BIRMINGHAM, Day.FRIDAY);
        verify(delegate, times(1)).forecastFor(Region.BIRMINGHAM, Day.FRIDAY);
    }
}
