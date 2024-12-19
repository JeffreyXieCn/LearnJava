package io.github.jeffreyxiecn.multithreading;
import java.util.concurrent.*;

public class ParallelTaskExecutorWithLatch {

    public static void main(String[] args) {
        // Create a fixed thread pool of size 5
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // CountDownLatch to track 20 tasks
        CountDownLatch latch = new CountDownLatch(20);

        try {
            for (int i = 1; i <= 20; i++) {
                int taskId = i;
                executorService.submit(() -> {
                    try {
                        System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
                        Thread.sleep(1000); // Simulate task work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        latch.countDown(); // Decrement latch count when task completes
                    }
                });
            }

            // Wait for all tasks to finish
            latch.await();

            System.out.println("All tasks completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Shut down the executor service
            executorService.shutdown();
        }
    }
}

