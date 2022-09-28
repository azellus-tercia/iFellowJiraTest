package PageObject.PageSteps;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import static PageObject.PageElements.ProfilePageElements.*;
import static utils.Configuration.getConfigurationValue;

public final class ProfilePageElementsSteps {
    public static String getDisplayedUsername() {
        return displayedUserName.getText().trim();
    }

    @Then("Пользователь {string} авторизован")
    public static void checkUserIsLogged(String username) {
        Assertions.assertEquals(username, getDisplayedUsername(),
                "Пользователь " + getConfigurationValue("username") + " не авторизован.");
    }
}
