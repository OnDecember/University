package ua.javarush.task.jdk13.task13.task1305;

/* 
Усе, що рухається
*/

public class Solution {

    public static void main(String[] args) throws Exception {


    }

    public interface CanMove {
        Double speed();
    }

    public interface CanFly extends CanMove {
        Double speed(CanFly canFly);
    }
}