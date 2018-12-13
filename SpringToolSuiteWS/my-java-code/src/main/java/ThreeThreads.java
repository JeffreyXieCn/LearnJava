/*
Question1:
Thread1: print 1, 4, 7, 10...
Thread2: print 2, 5, 8, 11...
Thread3: print 3, 6, 9, 12...
manage thread1/2/3 to print 1, 2, 3, 4, 5, ....
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreeThreads {

  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();

    int numOfThreads = 3;
    PrintController pc = new PrintController(0, numOfThreads);
    for (int i = 1; i <= numOfThreads; i++) {
      PrintTask pt = new PrintTask(i, numOfThreads, pc);
      exec.execute(pt);
    }

    //    PrintTask pt1 = new PrintTask(1, pc);
    //    PrintTask pt2 = new PrintTask(2, pc);
    //    PrintTask pt3 = new PrintTask(3, pc);
    //    exec.execute(pt1); // 1, 4, 7
    //    exec.execute(pt2); // 2, 5, 8
    //    exec.execute(pt3); // 3, 6, 9

    TimeUnit.SECONDS.sleep(5); // Run for a while...
    exec.shutdownNow(); // Interrupt
  }
}

class PrintController {
  private int lastPrintValue;
  private int numOfThreads;

  public PrintController(int initVal, int noThreads) {
    lastPrintValue = initVal;
    numOfThreads = noThreads;
  }

  public synchronized void setLastPrintValue(int val) {
    lastPrintValue = val;
    notifyAll();
  }

  public synchronized void waitUntilRemainder(int remainder) throws InterruptedException {
    while (lastPrintValue % numOfThreads != remainder) {
      wait();
    }
  }
}

class PrintTask implements Runnable {
  private int value;
  private int remainder;
  private int step;
  private PrintController pc;

  public PrintTask(int initVal, int step, PrintController p) {
    value = initVal;
    remainder = value - 1;
    this.step = step;
    pc = p;
  }

  @Override
  public void run() {
    // System.out.println("Enter run()");
    try {
      while (!Thread.interrupted()) {
        pc.waitUntilRemainder(remainder);
        System.out.print(value + " ");
        pc.setLastPrintValue(value);
        TimeUnit.MILLISECONDS.sleep(200); // so that we can observe the result
        value += step;
      }
    } catch (InterruptedException e) {
      // System.out.println("Exiting via interrupt");
    }
    // System.out.println("Exit run()");
  }
}
