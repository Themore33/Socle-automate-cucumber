package stepdef;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		plugin = { "pretty", "junit:target/junit-report/automate-admin-junit-report.xml" },
        features = "src/test/resources/features"
)
public class CucumberTestSuite {
}
