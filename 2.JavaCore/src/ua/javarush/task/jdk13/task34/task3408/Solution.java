package ua.javarush.task.jdk13.task34.task3408;

/* 
Генератор об'єктів
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        Generator<Event> eventGenerator = new Generator<>(Event.class);

        System.out.println(eventGenerator.newInstance().getId());
        System.out.println(eventGenerator.newInstance().getId());
        System.out.println(eventGenerator.newInstance().getId());
    }
}
