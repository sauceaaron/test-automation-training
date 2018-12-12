package com.google.weather;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleWeather
{
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(id="wob_loc")
	public WebElement location;

	@FindBy(id="wob_dts")
	public WebElement date;

	@FindBy(id="wob_dc")
	public WebElement sky;

	@FindBy(id="wob_pp")
	public WebElement precipitation;

	@FindBy(id="wob_hm")
	public WebElement humidity;

	@FindBy(id="wob_ws")
	public WebElement wind;

	@FindBy(id="wob_tm")
	public WebElement temperatureFahrenheit;

	@FindBy(id="wob_ttm")
	public WebElement temperatureCelsius;

	@FindBy(css="[class='vk_bk wob-unit'] > [aria-label=°Fahrenheit]")
	public WebElement FahrenheitLabel;

	@FindBy(css="[class='vk_bk wob-unit'] > [aria-label=°Celsius]")
	public WebElement CelsiusLabel;

	@FindBy(css="[class='vk_bk wob-unit'] > a:first-of-type")
	public WebElement useFahrenheitLink;

	@FindBy(css="[class='vk_bk wob-unit'] > a:last-of-type")
	public WebElement useCelsiusLink;

	@FindBy(css=".wob-unit > a[style*=inline]")
	public WebElement tempUnitsLink;

	public GoogleWeather(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	public GoogleWeather forCity(String city)
	{
		driver.get("https://www.google.com/search?q=weather+" + city);
		return this;
	}

	public String getLocation()
	{
		return location.getText();
	}

	public String getTemperature()
	{
		if (! temperatureFahrenheit.getAttribute("style").contains("display: none"))
		{
			return temperatureFahrenheit.getText();
		}

		if (! temperatureCelsius.getAttribute("style").contains("display: none"))
		{
			return temperatureCelsius.getText();
		}

		return "temperature not found";
	}

	public String getTemperatureUnits()
	{
		WebElement F = driver.findElement(By.cssSelector("[class='vk_bk wob-unit'] > a:first-of-type"));
		WebElement C = driver.findElement(By.cssSelector("[class='vk_bk wob-unit'] > a:last-of-type"));

		if (F.getAttribute("style").contains("display: none"))
		{
			return FahrenheitLabel.getText();
		}

		if (C.getAttribute("style").contains("display: none"))
		{
			return CelsiusLabel.getText();
		}

		return "temperature units not found";
	}

	public GoogleWeather useFahrenheit()
	{
		if (! useFahrenheitLink.getAttribute("style").contains("display: none"))
		{
			useFahrenheitLink.click();
		}

		return this;
	}

	public GoogleWeather useCelsius()
	{
		if (! useCelsiusLink.getAttribute("style").contains("display: none"))
		{
			useCelsiusLink.click();
		}

		return this;
	}

	public String getCurrentTemperature()
	{
		StringBuilder message = new StringBuilder();
		message.append(" current temperature for " + getLocation());
		message.append(" on " + date.getText());
		message.append(" is " + getTemperature());
		message.append(getTemperatureUnits());

		return message.toString();
	}
}
