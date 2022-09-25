package hooks;

import PageObject.PageSteps.HeaderElementsSteps;
import PageObject.PageSteps.LogInElementsSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import static utils.Configuration.getConfigurationValue;

public abstract class WebHooks {
    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
        Selenide.open(getConfigurationValue("jiraUrl"));
        new LogInElementsSteps().authorization(getConfigurationValue("username"),
                getConfigurationValue("password"));
    }

    @BeforeEach
    public void logIn() {
        new HeaderElementsSteps().clickMainLogo();
    }

    @AfterAll
    static void closeWebBrowser() {
        new HeaderElementsSteps().logout();
        Selenide.closeWebDriver();
    }
}
