package ua.javarush.task.jdk13.task23.task2301;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public static void main(String[] args) {

    }

    public static Solution[] getTwoSolutions() {
        Solution solution1 = new Solution();
        Solution solution2 = new Solution();
        solution1.innerClasses[0] = new InnerClass();
        solution1.innerClasses[1] = new InnerClass();
        solution2.innerClasses[0] = new InnerClass();
        solution2.innerClasses[1] = new InnerClass();
        return new Solution[] {solution1, solution2};
    }

    public static class InnerClass {
    }
}
