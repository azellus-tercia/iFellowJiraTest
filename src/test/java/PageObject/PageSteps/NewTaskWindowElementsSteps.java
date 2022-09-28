package PageObject.PageSteps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;

import static PageObject.PageElements.NewTaskWindowElements.*;
import static com.codeborne.selenide.Selenide.*;

public final class NewTaskWindowElementsSteps {
    public static void setTaskType(String taskType) {
        taskTypeSelector.shouldBe(Condition.visible).click();
        taskTypeSelector.sendKeys(taskType + "\n");
    }

    public static void setTaskName(String taskName) {
        taskThemeField.shouldBe(Condition.visible).setValue(taskName);
    }

    public static void setTaskDescription(String description) {
        taskDescriptionField.shouldBe(Condition.visible).click();
        actions().sendKeys(description).perform();
    }

    public static void setTaskEnvironment(String environment) {
        taskEnvironmentField.shouldBe(Condition.visible).click();
        actions().sendKeys(environment).perform();
    }

    public static void setConnectedTask(String task) {
        taskConnected.sendKeys(task);
    }

    @And("Сохранить и создать задачу")
    public static void acceptAndCreateTask() {
        submitButton.click();
    }

    @And("Заполнить поля задачи: {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public static void setTaskFields(String taskType, String taskName, String taskDescription, String taskFixInVersion,
                                     String taskEnvironment, String taskAffectedVersion, String connectedTask) {
        setTaskType(taskType);
        setTaskName(taskName);
        setTaskDescription(taskDescription);
        setTaskFixInVersion(taskFixInVersion);
        setTaskEnvironment(taskEnvironment);
        setTaskAffectedVersion(taskAffectedVersion);
        setConnectedTask(connectedTask);
    }
}
