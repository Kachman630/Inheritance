package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Epic;
import ru.netology.domain.Meeting;
import ru.netology.domain.SimpleTask;
import ru.netology.domain.Task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldFindMultipleTasksWhenSearching() {
        // 1) Находится несколько задач
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        Epic task2 = new Epic(2, new String[]{"Починить кран", "Купить яйца"});
        Meeting task3 = new Meeting(3, "Совещание", "НетоБанк", "10:00");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = { task1, task2 };
        Task[] actual = todos.search("Купить");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindExactlyOneTaskWhenSearching() {
        // 2) Находится ровно одна задача
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        Epic task2 = new Epic(2, new String[]{"Починить кран", "Купить яйца"});
        Meeting task3 = new Meeting(3, "Совещание", "НетоБанк", "10:00");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = { task3 };
        Task[] actual = todos.search("НетоБанк");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindZeroTasksWhenSearching() {
        // 3) Находится 0 задач (ни одна не подходит)
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        Epic task2 = new Epic(2, new String[]{"Починить кран", "Купить яйца"});
        Meeting task3 = new Meeting(3, "Совещание", "НетоБанк", "10:00");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = {};
        Task[] actual = todos.search("Самолет");

        assertArrayEquals(expected, actual);
    }
}