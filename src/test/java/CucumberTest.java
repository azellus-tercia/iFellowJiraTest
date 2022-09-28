import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"cucumber", "hooks", "PageObject"},
        tags = "not @ignore"
)
public class CucumberTest {
}
