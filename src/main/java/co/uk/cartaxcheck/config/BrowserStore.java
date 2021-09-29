package co.uk.cartaxcheck.config;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BrowserStore {
    private static final Logger LOG = LoggerFactory.getLogger(BrowserStore.class);

    Map<String,WebDriver> browsers = new HashMap<String,WebDriver>();

    public Map<String, WebDriver> getBrowsers() {
        return browsers;
    }

    public WebDriver getBrowser(String key) {
        WebDriver browser = browsers.get(key);
        if(browser != null) {
            LOG.info("Returning browser '{}' from browser store", browser);
        }
        return browser;
    }

    public WebDriver getBrowser() {
        for (String key : browsers.keySet()) {
            LOG.info("Looping through available browsers to ruturn");
            return browsers.get(key);
        }
        return null;
    }

    public void addBrowsers(String key,WebDriver driver){
        browsers.put(key,driver);
        LOG.info("Added a browser to browser store "+key+":"+driver);
    }

    public void clearBrowsers(){
        browsers.clear();
        LOG.info("Cleared all browsers from browser store ");
    }

}
