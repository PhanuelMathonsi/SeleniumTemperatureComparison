import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WeatherSA {

	WebDriver driver;

	public WeatherSA(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// creating web elements using page factory
	@FindBy(id = "search")
	WebElement search;

	@FindBy(id = "submitsearch")
	WebElement citySearchSubmit;

	By cityTemperature = By.cssSelector(".ctemp-0");

	public void citySearch(String cityName) {
		search.sendKeys(cityName);
		citySearchSubmit.click();
	}

	public int getSATemperature(int tempIndex) {
		waitingForElementToAppear(cityTemperature);
		return Integer.parseInt(driver.findElement(cityTemperature).getText().split("°")[tempIndex].trim());
	}

	public void waitingForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void goTo(String url) {
		driver.get(url);
	}

}
