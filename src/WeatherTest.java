import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WeatherTest {

	String city = "Polokwane";

	WebDriver driverForWeatherSA;
	WebDriver driverForAccuWeather;
	AccuWeather accu_weather;
	WeatherSA sa_weather;

	@Before
	public void setUp() {

		System.setProperty("webdiver.chrome.driver", "C:\\Users\\Phanuel.Mathonsi\\Documents\\driver\\chromedriver.exe");

		// for WeatherSA
		driverForWeatherSA = new ChromeDriver();
		sa_weather = new WeatherSA(driverForWeatherSA);
		sa_weather.goTo("https://www.weathersa.co.za/");
		sa_weather.citySearch(city);

		// for AccuWeather
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		driverForAccuWeather = new ChromeDriver(options);
		driverForAccuWeather.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driverForAccuWeather.get("https://www.accuweather.com/");
		accu_weather = new AccuWeather(driverForAccuWeather);
		accu_weather.citySearch(city);

	}

	@Test
	public void compareMaximum() {
		
		int maximumTempForSA = sa_weather.getSATemperature(0);
		
		int maximumTempForAccu = accu_weather.getTemperature(accu_weather.accuCityMaximumTemperature);

		Assert.assertTrue("Maximum temperatures are not equal.", maximumTempForSA == maximumTempForAccu);

		// This line will only be executed if temperatures are equal
		System.out.println("Maximum temperatures are equal.");

	}

	@Test
	public void compareMinimum() {
		
		int minimumTempForSA = sa_weather.getSATemperature(1);
		
		int minimumTempForAccu = accu_weather.getTemperature(accu_weather.accuCityMinimumTemperature);
		
		Assert.assertTrue("Minimum temperatures are not equal.", minimumTempForSA == minimumTempForAccu);

		// This line will only be executed if temperatures are equal
		System.out.println("Maximum temperatures are equal.");

	}

	@After
	public void closeC() {
		
		driverForWeatherSA.close();
		
		driverForAccuWeather.close();
	}

}
