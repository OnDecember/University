package ua.javarush.task.jdk13.task21.task2103;

import java.util.Date;
import java.util.Objects;

/* 
Помилка в equals/hashCode
*/

public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Solution)) {
            return false;
        }

        Solution solution1 = (Solution) o;

        return Objects.equals(anInt, solution1.anInt)
                && Objects.equals(string, solution1.string)
                && Objects.equals(aDouble, solution1.aDouble)
                && Objects.equals(date, solution1.date)
                && Objects.equals(solution, solution1.solution);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {

    }
}
