package ua.javarush.task.pro.task15.task1514;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Усе відносне
*/

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        Path path1 = Path.of(str1);
        Path path2 = Path.of(str2);
        try {
            System.out.println(path1.relativize(path2));
        } catch (IllegalArgumentException e) {
            try {
                System.out.println(path2.relativize(path1));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

