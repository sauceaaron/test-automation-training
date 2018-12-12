package com.google.weather;

import com.saucelabs.SauceLabsParallelTestBase;
import com.saucelabs.junit.ConcurrentParameterized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(ConcurrentParameterized.class)
public class SauceLabsParameterizedPageObjectTest extends SauceLabsParallelTestBase
{
	@Parameterized.Parameters(name = "{index}: com.google.weather.WeatherTest({0})={1}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(
				"Seattle",
				"Miami",
				"Denver",
				"Honolulu",
				"San Francisco",
				"Beijing",
				"Adelaide",
				"Jerusalem",
				"Porto Alegre",
				"Mumbai"
		);
	}

	String city;

	public SauceLabsParameterizedPageObjectTest(String city)
	{
		this.city = city;
	}

	@Test
	public void getTemperatureForCity()
	{
		GoogleWeather weather = new GoogleWeather(driver).forCity(city).useCelsius();
		weather.getCurrentTemperature();

		assertThat(weather.getLocation()).contains(city);
		assertThat(weather.getTemperatureUnits().endsWith("C"));
	}
}