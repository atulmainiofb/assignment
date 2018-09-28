package main.java;

import main.java.tasks.FirstTask;
import main.java.tasks.LastTask;
import main.java.tasks.OtherTask;
import main.java.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskFetcher {

    public static List<Task> fetchTasks() {
        List<Task> tasks = new ArrayList<>();
        FirstTask firstTask = new FirstTask();
        tasks.add(firstTask);
        Random generator = new Random();
        Integer randomNumberOfTasks = generator.nextInt(100);
        for (int i = 0; i < randomNumberOfTasks; i++) {
            OtherTask otherTask = new OtherTask();
            tasks.add(otherTask);
        }
        LastTask lastTask = new LastTask();
        tasks.add(lastTask);
        return tasks;
    }
}
