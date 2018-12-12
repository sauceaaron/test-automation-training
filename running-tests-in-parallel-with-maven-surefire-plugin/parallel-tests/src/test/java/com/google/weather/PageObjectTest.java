package com.google.weather;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class PageObjectTest
{
	WebDriver driver;

	@Test
	public void getWeatherForSeattle()
	{
		GoogleWeather weather = new GoogleWeather(driver).forCity("Seattle").useFahrenheit();
		String currentTemperature = weather.getCurrentTemperature();
		printThreadAnd(currentTemperature);

		assertThat(currentTemperature).contains("Seattle");
		assertThat(currentTemperature).endsWith("F");
	}

	@Test
	public void getWeatherForBeijing()
	{
		GoogleWeather weather = new GoogleWeather(driver).forCity("Beijing").useCelsius();
		String currentTemperature = weather.getCurrentTemperature();
		printThreadAnd(currentTemperature);

		assertThat(currentTemperature).contains("Beijing");
		assertThat(currentTemperature).endsWith("C");
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

	////////// helper methods

	private void printThreadAnd(String message)
	{
		System.out.println("[ Thread " + Thread.currentThread().getId() + "]" + message);
	}
}