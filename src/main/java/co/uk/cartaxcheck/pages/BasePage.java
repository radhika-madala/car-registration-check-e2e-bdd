package co.uk.cartaxcheck.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasePage {
	private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);

	private WebDriver driver;

	public BasePage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void sendKeys(WebElement element, String value){
		new WebDriverWait(driver,30).
		until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	public void clickOn(WebElement element) {
		new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}


	protected void waitForTextToBePresent(WebElement webElement){
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
}
