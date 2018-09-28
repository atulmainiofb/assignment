package main.java;

import main.java.tasks.Task;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        TaskExecutor taskExecutor = new TaskExecutor();

        TaskFetcher taskFetcher = new TaskFetcher();

        List<Task> tasks = taskFetcher.fetchTasks();

        String result = taskExecutor.executeTasks(tasks);

    }
}
