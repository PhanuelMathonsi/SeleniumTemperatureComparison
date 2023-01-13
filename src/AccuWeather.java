import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccuWeather {
	WebDriver driver;

	public AccuWeather(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// creating web elements using page factory
	@FindBy(className = "search-input")
	WebElement accuCitySearch;

	@FindBy(xpath = "//div[@class='search-bar-result search-result'][1]")
	WebElement accuCitySearchSubmit;

	@FindBy(css = "[data-qa='todayWeatherCard'] [class='temp']")
	WebElement accuCityMaximumTemperature;

	@FindBy(css = "[data-qa='tonightWeatherCard'] [class='temp']")
	WebElement accuCityMinimumTemperature;

	public int getTemperature(WebElement element) {
		return Integer.parseInt(element.getText().split("°")[0].trim());
	}

	public void citySearch(String cityName) {
		accuCitySearch.sendKeys(cityName);
		accuCitySearchSubmit.click();
	}
	public void goTo(String url) {
		driver.get(url);
	}

}
