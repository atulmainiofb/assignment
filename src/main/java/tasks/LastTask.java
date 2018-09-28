package main.java.tasks;

public class LastTask implements Task {
    @Override
    public void execute() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMessage() {
        return "Last Task";
    }
}
