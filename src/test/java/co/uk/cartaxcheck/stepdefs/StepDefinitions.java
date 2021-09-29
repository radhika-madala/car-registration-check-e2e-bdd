package co.uk.cartaxcheck.stepdefs;

import co.uk.cartaxcheck.CucumberSpringBootApplication;
import co.uk.cartaxcheck.ScenarioContext;
import co.uk.cartaxcheck.config.BrowserStore;
import co.uk.cartaxcheck.config.DriverManager;
import co.uk.cartaxcheck.pages.HomePage;
import co.uk.cartaxcheck.util.CarDetails;
import co.uk.cartaxcheck.util.FileUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(classes = CucumberSpringBootApplication.class)
public class StepDefinitions {
    private static final Logger LOG = LoggerFactory.getLogger(StepDefinitions.class);

    private HomePage homePage;

    @Autowired
    DriverManager driverManager;

    @Value("${application_url}")
    private String baseUrl;

    @Autowired
    ScenarioContext scenarioContext;

    @Autowired
    BrowserStore browserStore;

    @Value("${closeBrowserAfterEachScenario}")
    private boolean closeBrowserAfterEachScenario;

    WebDriver driver;
    protected List<String> carRegistrationsTestData;
    protected List<CarDetails> actualCarDetails;
    protected List<CarDetails> expectedVehicleDetails;
    FileUtil fileUtil;

    @Before
    public void before(Scenario scenario) throws IOException {
        driver = browserStore.getBrowser("chrome");
        if (driver == null) {
            driver = driverManager.initializeDriver();
        }
        userIsOnTheHomePage();
        LOG.info("Before each scenario");

    }

    @After
    public void after(Scenario scenario) throws IOException {
        if (closeBrowserAfterEachScenario) {
            browserStore.clearBrowsers();
            driver.close();
        }
        LOG.info("After each scenario");
    }

    @Given("user is on the home page")
    public void userIsOnTheHomePage() {
        driver.get(baseUrl);
        homePage = new HomePage(driver);
    }

    @Given("user has the test data file {string}")
    public void userHasTheTestDataFile(String fileName) {
        // here read from the file and add all registrations to list of strings
        fileUtil = new FileUtil();
        carRegistrationsTestData = fileUtil.readInputFileAndGetRegistrationNumbers(fileName);
    }

    @When("user submits car registration numbers to car tax check website")
    public void userSubmitsCarRegistrationNumbersToCarTaxCheckWebsite() throws InterruptedException {
        // Iterating over the list of registrations and submitting the website.
        actualCarDetails = new ArrayList<CarDetails>();
        for (String registration : carRegistrationsTestData) {
       
            homePage.searchForCarRegistration(registration);
            actualCarDetails.add(homePage.getVehicleDetails());
            driver.get(baseUrl);
         
        }
    }

    @Then("the outcome should match to the contents of this file {string}")
    public void theOutcomeShouldMatchToTheContentsOfThisFile(String outputFile) throws IOException, URISyntaxException {
        // Compare list of actual vehicle details with output.txt
        expectedVehicleDetails = fileUtil.readExpectedCarDetails(outputFile);
        assertExpectedAgainstActual(expectedVehicleDetails, actualCarDetails);
    }

    private void assertExpectedAgainstActual(List<CarDetails> expectedVehicleDetails, List<CarDetails> actualCarDetails) {
        List<CarDetails> notFoundInActual = new ArrayList<CarDetails>();
        for (CarDetails expectedCarDetails : expectedVehicleDetails){
            boolean isExpectedPresentInActual = false;
            for(CarDetails actualCarDetail: actualCarDetails){
                if(expectedCarDetails.getRegistration().equals(actualCarDetail.getRegistration())){
                    LOG.info("Expected car details: {} match with Actual Car Details: {}  ",expectedCarDetails.getRegistration(),actualCarDetail.getRegistration());
                    isExpectedPresentInActual = true;
                    Assert.assertEquals(expectedCarDetails.getRegistration(),actualCarDetail.getRegistration());
                    Assert.assertEquals(expectedCarDetails.getMake(),actualCarDetail.getMake());
                    Assert.assertEquals(expectedCarDetails.getModel(),actualCarDetail.getModel());
                    Assert.assertEquals(expectedCarDetails.getColor(),actualCarDetail.getColor());
                    Assert.assertEquals(expectedCarDetails.getYear(),actualCarDetail.getYear());
                }
            }
            if (!isExpectedPresentInActual) {
                notFoundInActual.add(expectedCarDetails);
            }
        }
        if (!notFoundInActual.isEmpty()) {
            for(CarDetails carDetail: notFoundInActual) {
                Assert.fail("Expected car details " + carDetail.getRegistration() +
                        " does not have any match in Actual Car Details ");
            }
        }

    }
}


