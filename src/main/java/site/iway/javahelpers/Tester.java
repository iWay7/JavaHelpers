package site.iway.javahelpers;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Tester {

    private static final int WORKER_COUNT = 3;

    private static CountDownLatch sCountDownLatch = new CountDownLatch(WORKER_COUNT);

    private static class WorkThread extends Thread {

        public void run() {
            System.out.println(Thread.currentThread().getName() + " start working...");
            try {
                Random random = new Random(System.nanoTime());
                int workTime = random.nextInt(3000);
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                // nothing
            }
            System.out.println(Thread.currentThread().getName() + " finish working.");
            sCountDownLatch.countDown();
        }

    }

    public static void main(String[] args) {
        System.out.println("MainThread start creating workers...");
        for (int i = 0; i < WORKER_COUNT; i++) {
            WorkThread workThread = new WorkThread();
            workThread.setName("WorkThread-" + i);
            workThread.start();
        }
        System.out.println("MainThread finished creating workers, and begin wait for workers to finish their jobs...");

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sCountDownLatch.await();
                    System.out.println("aaa");
                } catch (InterruptedException e) {
                    // nothing
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sCountDownLatch.await();
                    System.out.println("bbb");
                } catch (InterruptedException e) {
                    // nothing
                }
            }
        }.start();

        System.out.println("All workers finished their jobs, MainThread continue. ");
    }

}