package main.java.tasks;

public class OtherTask implements Task {
    @Override
    public void execute() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMessage() {
        return "Other Task";
    }
}
