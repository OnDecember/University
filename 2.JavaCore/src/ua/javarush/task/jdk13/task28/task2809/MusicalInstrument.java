package ua.javarush.task.jdk13.task28.task2809;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class MusicalInstrument implements Callable<String> {

    private final String name;

    public MusicalInstrument(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        int random = ThreadLocalRandom.current().nextInt(2000);
        System.out.println("Починає грати: " + name + ". Тривалість: " + random + "ms");
        Thread.sleep(random);
        System.out.println(name + " закінчує грати");
        return name;
    }
}
