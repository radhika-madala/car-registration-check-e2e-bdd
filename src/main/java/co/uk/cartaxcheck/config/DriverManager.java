package co.uk.cartaxcheck.config;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Component
public class DriverManager {
    private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);

    public DriverManager(){
        /* Create /actual if doesn't exist */
        File file = new File("log");
        if (!file.exists()) {
            file.mkdirs();
        }
    }
@Autowired
BrowserStore browserStore;
    @Value("${browser}")
    private String browser;

    @Value("${implicitWait}")
    private Long implicitWait;

    @Value("${headless}")
    private boolean headless;

    public WebDriver initializeDriver() {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            driver = (WebDriver) browserStore.getBrowser(browser);
            if (driver == null) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("ignore-certificate-errors");
                if(headless) {
                    chromeOptions.addArguments("headless");
                }
                driver = new ChromeDriver(chromeOptions);
                browserStore.addBrowsers(browser, driver);
                LOG.info("Initialized {}", browser);
            }
            } else if (browser.equalsIgnoreCase("ie")) {
                driver = (WebDriver) browserStore.getBrowser(browser);
                if (driver == null) {
                    driver = new InternetExplorerDriver();
                    browserStore.addBrowsers(browser, driver);
                    LOG.info("Initialized {}", browser);
                }
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = (WebDriver) browserStore.getBrowser(browser);
                if (driver == null) {
                    driver = new FirefoxDriver();
                    browserStore.addBrowsers(browser, driver);
                    LOG.info("Initialized {}", browser);
                }
            } else {
                LOG.error("The browser '{}' you provided is not supported yet ", browser);
            }
            driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
          return driver;
    }
}
