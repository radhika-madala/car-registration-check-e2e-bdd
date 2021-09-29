
package co.uk.cartaxcheck;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber",
                "json:target/cucumber.json" },
        features = {"src/test/resources/features"},
        tags = "@CAR_CHECK_WITH_DATA_FILE"
        )
public class RunCucumberTest {

}