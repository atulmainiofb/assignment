package main.java;

import main.java.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TaskExecutor {

    private final int poolSize = Runtime.getRuntime().availableProcessors() / 2;

    private final static Logger logger = Logger.getLogger(TaskExecutor.class.getName());

    private static FileHandler fileHandler;

    private static SimpleFormatter simpleFormatter;

    public TaskExecutor() {
        initLogger();
    }

    public String executeTasks(List<Task> tasks) {

        logger.info("Executing tasks " + tasks.size());

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<Future> taskFuture = new ArrayList<>();
        executeSingleTask(tasks.get(0));

        for (int i = 1; i < tasks.size() - 1; i++) {

            Task task = tasks.get(i);

            Future future = executor.submit(new Runnable() {
                @Override
                public void run() {

                    logger.info("Started task for " + task.getMessage());
                    task.execute();
                    logger.info("Completed task for " + task.getMessage());

                }
            });
            taskFuture.add(future);
        }

        for (Future future : taskFuture) {
            try {
                future.get();
            } catch (Exception e) {
                logger.info("Exception occured while getting response for " + future + " and reason is " + e);
            }
        }
        executeSingleTask(tasks.get(tasks.size() - 1));


        logger.info("All tasks ran successfully");


        return "Tasks Successfully Executed";
    }

    private void initLogger() {
        logger.setLevel(Level.ALL);
        try {
            fileHandler = new FileHandler("taskLogger.log");
            simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Logger is initialized Successfully");
    }

    private void executeSingleTask(Task task) {
        logger.info("Task started for " + task.getMessage());
        task.execute();
        logger.info("Task completed for " + task.getMessage());
    }
}
