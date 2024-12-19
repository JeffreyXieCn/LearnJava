package io.github.jeffreyxiecn.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelTaskExecutor {

    public static void main(String[] args) {
        // Create a fixed thread pool of size 5
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {
            // Create a list to hold 20 similar tasks
            List<Callable<String>> tasks = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                int taskId = i;
                tasks.add(() -> {
                    System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
                    Thread.sleep(1000); // Simulate task work
                    return "Task " + taskId + " completed.";
                });
            }

            long beginTime = System.currentTimeMillis();
            System.out.println("Begin time: " + beginTime);
            // Submit all tasks to the executor and wait for completion
            List<Future<String>> results = executorService.invokeAll(tasks);
            long endTime = System.currentTimeMillis();
            System.out.println("End time: " + endTime);
            System.out.println("Duration: " + (endTime - beginTime) + " ms"); // Calculate the duration of the execution

            // Process the results after all tasks are finished
            for (Future<String> result : results) {
                System.out.println(result.get());
            }

            System.out.println("All tasks completed.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Shut down the executor service
            executorService.shutdown();
        }
    }
}

