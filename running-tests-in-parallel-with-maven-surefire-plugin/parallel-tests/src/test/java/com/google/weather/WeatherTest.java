package com.google.weather;

import com.saucelabs.SauceLabsParallelTestBase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherTest extends SauceLabsParallelTestBase
{
	@Test
	public void getCurrentTemperatureForSeattle()
	{
		String city = "Seattle";

		GoogleWeather weather = new GoogleWeather(driver).forCity(city);
		String currentTemperature = weather.getCurrentTemperature();

		assertThat(currentTemperature).contains(city);
	}

	@Test
	public void getCurrentTemperatureForDenver()
	{
		String city = "Denver";

		GoogleWeather weather = new GoogleWeather(driver).forCity(city);
		String currentTemperature = weather.getCurrentTemperature();

		assertThat(currentTemperature).contains(city);
	}

	@Test
	public void getCurrentTemperatureForSanFrancisco()
	{
		String city = "San Francisco";

		GoogleWeather weather = new GoogleWeather(driver).forCity(city);
		String currentTemperature = weather.getCurrentTemperature();

		assertThat(currentTemperature).contains(city);
	}

	@Test
	public void getCurrentTemperatureForBoston()
	{
		String city = "Boston";

		GoogleWeather weather = new GoogleWeather(driver).forCity(city);
		String currentTemperature = weather.getCurrentTemperature();

		assertThat(currentTemperature).contains(city);
	}

	@Test
	public void getCurrentTemperatureForMiami()
	{
		String city = "Miami";

		GoogleWeather weather = new GoogleWeather(driver).forCity(city);
		String currentTemperature = weather.getCurrentTemperature();

		assertThat(currentTemperature).contains(city);
	}
}
