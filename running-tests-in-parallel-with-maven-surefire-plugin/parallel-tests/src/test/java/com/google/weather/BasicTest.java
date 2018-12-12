package com.google.weather;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicTest
{
	@Test
	public void getWeatherForSeattle()
	{
		String city = "Seattle";

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get("https://www.google.com/search?q=weather+" + city);

		String location = driver.findElement(By.id("wob_loc")).getText();
		String date = driver.findElement(By.id("wob_dts")).getText();
		String sky = driver.findElement(By.id("wob_dc")).getText();
		String precipitation = driver.findElement(By.id("wob_pp")).getText();
		String humidity = driver.findElement(By.id("wob_hm")).getText();
		String wind = driver.findElement(By.id("wob_ws")).getText();

		String temperature = driver.findElement(By.id("wob_tm")).getText();
		String temperatureUnits = driver.findElement(By.cssSelector("[class='vk_bk wob-unit'] > span:not(.aria_disable)")).getText();

		StringBuilder message = new StringBuilder();
		message.append("[thread:" + Thread.currentThread().getId() + "]");
		message.append(" current temperature for " + location);
		message.append(" on " + date);
		message.append(" is " + temperature + " " + temperatureUnits);

		System.out.println(message);

		assertThat(location).contains(city);

		driver.quit();
	}
}