import com.google.weather.GoogleWeather;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest
{
	WebDriver driver;

	@Before
	public void setup()
	{
		driver = new ChromeDriver();
	}

	@Test
	public void getWeather()
	{
		String city = "Seattle";

		GoogleWeather weather = new GoogleWeather(driver).forCity(city).useCelsius();
		System.out.println("Thread " + Thread.currentThread().getId() + " : " + weather.getCurrentTemperature());

		assertThat(weather.getLocation()).contains(city);
		assertThat(weather.getTemperatureUnits().endsWith("C"));
	}

	@After
	public void teardown()
	{
		if (driver != null) { driver.quit(); }
	}
}
