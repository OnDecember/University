package ua.javarush.task.jdk13.task28.task2815;

import java.util.Random;
import java.util.concurrent.*;

/* 
Пакуємо лампочки
*/

public class Solution {

    private static final BlockingQueue<String> QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ExecutorService service1 = Executors.newSingleThreadExecutor();
        ExecutorService service2 = Executors.newCachedThreadPool();
        ExecutorService service3 = Executors.newWorkStealingPool();
        runProcess(service1);
        runProcess(service2);
        runProcess(service3);
    }

    public static void runProcess(ExecutorService consumerService) {
        System.out.println("--------------------------------------");
        simulatingProducers();
        startConsumers(consumerService);
    }

    private static void simulatingProducers() {
        int maxProdBulbs = 5_000_000;
        Random rnd = new Random();
        System.out.println("Емулюємо роботу виробників...");
        System.out.println("Виробники виготовили " + maxProdBulbs + " лампочок...");

        for (int i = 0; i < maxProdBulbs; i++) {
            QUEUE.add("Лампочка #" + rnd.nextInt(1000));
        }

    }

    private static void startConsumers(ExecutorService consumerService) {
        System.out.println("Для пакування використовується: " + consumerService.getClass().getSimpleName());

        int queueSize = QUEUE.size();
        Consumer consumer = new Consumer();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < queueSize; i++) {
            consumerService.execute(consumer);
        }

        consumerService.shutdown();
        try {
            consumerService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            System.out.println("Пакувальники спакували всі лампочки за " + (System.currentTimeMillis() - startTime) + " мс");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            String bulb = QUEUE.poll();
//            if (bulb != null) {
//                System.out.println("Упакована: " + bulb + " пакувальником: " + Thread.currentThread().getName());
//            }
        }
    }
}
