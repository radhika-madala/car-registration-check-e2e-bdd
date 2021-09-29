package co.uk.cartaxcheck.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.uk.cartaxcheck.util.CarDetails;

public class HomePage extends BasePage {
	private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);

	private WebDriver driver;

	@FindBy(id = "vrm-input")
	private WebElement carRegCheckInput;

	@FindBy(className = "jsx-1164392954")
	private WebElement freeCarCheckBtn;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public HomePage searchForCarRegistration(String registrationNUmber) throws InterruptedException {

		sendKeys(carRegCheckInput, registrationNUmber);
		clickOn(freeCarCheckBtn);
		Thread.sleep(3000);
		return this;
	}

	public CarDetails getVehicleDetails() {
		List<WebElement> allDetails = driver.findElements(By.className("jsx-3496807389"));
		CarDetails carDetails = new CarDetails();
		carDetails.setRegistration(allDetails.get(0).getText());
		carDetails.setMake(allDetails.get(1).getText());
		carDetails.setModel(allDetails.get(2).getText());
		carDetails.setColor(allDetails.get(3).getText());
		carDetails.setYear(allDetails.get(4).getText());
		return carDetails;
	}
}
