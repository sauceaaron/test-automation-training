package com.google.weather;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ParameterizedPageObjectTest
{
	WebDriver driver;

	String city;

	public ParameterizedPageObjectTest(String city)
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

	////////// setup and teardown

	@Before
	public void setup()
	{
		driver = new ChromeDriver();
	}

	@After
	public void teardown()
	{
		if (driver != null) { driver.quit(); }
	}

	////////// parameters

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
}