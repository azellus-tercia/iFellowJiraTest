package PageObject.PageSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static PageObject.PageElements.HeaderElements.*;

import static PageObject.PageSteps.ProjectPageElementsSteps.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public final class HeaderElementsSteps {
    public void logout() {
        userHeaderMenuButton.click();
        logOutButton.click();
    }

    @When("Открыть страницу пользователя")
    public static void goToUserPage() {
        userHeaderMenuButton.click();
        profileButton.click();
    }

    @When("Открыть проект {string}")
    public static void openProject(String projectName) {
        projectsButton.click();
        $(byText(projectName)).click();
    }

    @And("Найти задачу под номером {string}")
    public static void searchTask(String projectName) {
        searchField.setValue(projectName + "\n");
    }

    @And("Найти и открыть созданную задачу {string}")
    public static void searchCreatedTask(String projectName) {
        searchTask(getTaskTestNumber(projectName));
    }

    public void clickMainLogo() {
        headerLogo.click();
    }
}
