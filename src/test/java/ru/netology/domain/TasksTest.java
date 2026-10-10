package ru.netology.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TasksTest {

    @Test
    public void simpleTaskShouldMatchTitle() {
        SimpleTask task = new SimpleTask(1, "Купить молоко");
        assertTrue(task.matches("Купить"));
        assertTrue(task.matches("молоко"));
        assertFalse(task.matches("хлеб"));
    }

    @Test
    public void epicShouldMatchAnySubtask() {
        Epic task = new Epic(2, new String[]{"Молоко", "Хлеб"});
        assertTrue(task.matches("Молоко"));
        assertTrue(task.matches("Хлеб"));
        assertFalse(task.matches("Яйца"));
    }

    @Test
    public void meetingShouldMatchTopicOrProject() {
        Meeting task = new Meeting(3, "Встреча с банком", "Нетология", "Завтра в 15:00");
        assertTrue(task.matches("Встреча"));
        assertTrue(task.matches("Нетология"));
        assertFalse(task.matches("Завтра"));
    }
}