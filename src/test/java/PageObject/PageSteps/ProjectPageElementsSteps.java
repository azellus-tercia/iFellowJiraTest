package PageObject.PageSteps;

import static PageObject.PageElements.ProjectPageElements.*;
import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public final class ProjectPageElementsSteps {
    public static String getProjectName() {
        return projectName.getAttribute("title");
    }

    @Then("Проверить, что открытый проект называется {string}")
    public static void checkProjectName(String projectName) {
        Assertions.assertEquals(projectName, getProjectName(), "Проект " + projectName + " не найден.");
    }

    @And("Открыть список задач")
    public static void clickTasks() {
        tasksButton.click();
        filtersButton.shouldBe(Condition.visible);
    }

    @And("Поменять фильтр отображения задач на {string}")
    public static void changeFiltersTo(String filtersType) {
        filtersButton.click();
        $(byText(filtersType)).click();
    }

    public static String getNumberOfTasks() {
        String number = numberOfTasks.getText();
        return numberOfTasks.shouldNotHave(Condition.exactText(number)).getText().substring("1 из ".length());
    }

    @Then("Вывести общее кличество задач")
    public static void printNumberOfTasks() {
        System.out.println(getNumberOfTasks());
    }

    @And("Нажать \"Создать задачу\" внизу экрана и выбрать \"Открыть в диалоговом окне\"")
    public static void newTaskWithDialogue() {
        createTaskButton.click();
        openInDialogueButton.shouldBe(Condition.visible);
        openInDialogueButton.click();
        taskCreationHeader.shouldBe(Condition.visible);
    }

    public static String getTaskTestNumber(String taskName) {
        String taskDescription = $x("//div[@class='issue-content-container' and span[contains(., '" + taskName + "')]]").getText();
        return taskDescription.substring(0, taskDescription.indexOf('\n'));
    }
}
