import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(plugin = ['pretty', 'html:build/reports/cucumber'],
        strict=false,
        features=['src/test/resources/feature'],
        glue=['src/test/groovy/steps'])
class RunFeatureTests {
//leave it empty!
}