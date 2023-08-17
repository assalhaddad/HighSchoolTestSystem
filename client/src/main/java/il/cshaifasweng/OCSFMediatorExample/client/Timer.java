package il.cshaifasweng.OCSFMediatorExample.client;
import java.util.Scanner;

public class Timer {
    private static Thread timerThread;
    private static long startTimeMillis;
    private static long remainingDelayMillis;
    private static Runnable task;

    public static void main(String[] args) {
        scheduleTask();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Timer started. Enter 'extend' to extend the delay or 'cancel' to cancel the timer.");

        while (remainingDelayMillis > 0) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("extend")) {
                extendDelay();
            } else if (input.equalsIgnoreCase("cancel")) {
                cancelTimer();
                break;
            } else {
                System.out.println("Unknown command. Enter 'extend' or 'cancel'.");
            }
        }

        System.out.println("Program finished.");
    }

    private static void scheduleTask() {
        task = () -> {
            System.out.println("done");
            timerThread.interrupt(); // Interrupt the timer thread after the task is executed
        };

        timerThread = new Thread(() -> {
            try {
                Thread.sleep(5000); // Delay for 5 seconds
                task.run();
            } catch (InterruptedException e) {
                // Timer interrupted, do nothing
            }
        });

        startTimeMillis = System.currentTimeMillis();
        remainingDelayMillis = 5000; // Initial delay of 5 seconds

        timerThread.start();
    }

    private static void extendDelay() {
        timerThread.interrupt();

        // Calculate new remaining delay with an additional 5 seconds
        remainingDelayMillis += 5000;

        timerThread = new Thread(() -> {
            try {
                Thread.sleep(remainingDelayMillis);
                task.run();
            } catch (InterruptedException e) {
                // Timer interrupted, do nothing
            }
        });

        timerThread.start();
        System.out.println("Delay extended.");
    }

    private static void cancelTimer() {
        timerThread.interrupt();
        long endTimeMillis = System.currentTimeMillis();
        long totalTimeMillis = endTimeMillis - startTimeMillis;
        long totalTimeSeconds = totalTimeMillis / 1000;
        long remainingMillis = totalTimeMillis % 1000;

        System.out.println("Timer canceled.");
        System.out.println("Total time the timer ran: " + totalTimeSeconds + " seconds and " + remainingMillis + " milliseconds");
    }
}