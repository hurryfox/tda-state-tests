import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(plugin = ['pretty', 'html:build/reports/cucumber'],
        strict = false,
        features = ['src/test/resources/regression'],
        glue = ['src/test/groovy/steps'],
        junit = ['--filename-compatible-names'])
class RunRegressionTests {
//leave it empty!
}