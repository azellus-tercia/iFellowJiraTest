import hooks.WebHooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static PageObject.PageSteps.HeaderElementsSteps.*;
import static PageObject.PageSteps.NewTaskWindowElementsSteps.*;
import static PageObject.PageSteps.ProfilePageElementsSteps.getDisplayedUsername;
import static PageObject.PageSteps.ProjectPageElementsSteps.*;
import static PageObject.PageSteps.TaskPageElementsSteps.*;
import static utils.Configuration.getConfigurationValue;

public final class JiraTest extends WebHooks {
    private static final String PROJECT_NAME_WITH_CODE = "Test (TEST)";
    private static final String PROJECT_NAME = "Test";
    private static final String VERSION = "Version 2.0";
    private static final String TASK_NAME = "New Task Error";

    @Test
    @DisplayName("Проверка авторизации пользователя.")
    public void Test_UserIsAuthorized() {
        goToUserPage();

        Assertions.assertEquals(getConfigurationValue("username"), getDisplayedUsername(),
                "Пользователь " + getConfigurationValue("username") + " не авторизован.");
    }

    @Test
    @DisplayName("Проверка перехода на проект.")
    public void Test_ProjectIsOpen() {
        openProject(PROJECT_NAME_WITH_CODE);

        Assertions.assertEquals(PROJECT_NAME, getProjectName(), "Проект " + PROJECT_NAME + " не найден.");
    }

    @Test
    @DisplayName("Вывод количества задач в консоль.")
    public void Test_ShowNumberOfTasks() {
        openProject(PROJECT_NAME_WITH_CODE);
        clickTasks();
        changeFiltersTo("Все задачи");
        System.out.println(getNumberOfTasks());
    }

    @Test
    @DisplayName("Проверка статуса задачи и привязки затронутой версии.")
    public void Test_GetTaskStatus() {
        openProject(PROJECT_NAME_WITH_CODE);
        searchTask("TEST-21967");

        Assertions.assertTrue(getTaskStatus().length() > 0, "Статус задачи не задан.");
        Assertions.assertEquals(VERSION, getFixInVersion(), "Версия для исправления не соответствует " + VERSION);
    }

    @Test
    @DisplayName("Создание задачи и проверка смены статуса.")
    public void Test_CreateTask() {
        openProject(PROJECT_NAME_WITH_CODE);
        clickTasks();
        newTaskWithDialogue();
        setTaskType("Ошибка");
        setTaskName(TASK_NAME);
        setTaskDescription("Here is a task description.");
        setTaskFixInVersion2();
        setTaskEnvironment("Here is some environment.");
        setTaskAffectedVersion2();
        setConnectedTask("TEST-21967");
        acceptAndCreateTask();
        searchTask(getTaskTestNumber(TASK_NAME));

        Assertions.assertEquals("СДЕЛАТЬ", getTaskStatus(), "Начальный статус не равен 'Сделать'.");

        setStatusInProgress();
        Assertions.assertEquals("В РАБОТЕ", getChangedTaskStatus(), "Статус не равен 'В работе'.");

        setStatusResolved();
        Assertions.assertEquals("РЕШЕННЫЕ", getChangedTaskStatus(), "Статус не равен 'Решенные'.");

        setStatusDone();
        Assertions.assertEquals("ГОТОВО", getChangedTaskStatus(), "Статус не равен 'Готово'.");
    }
}
