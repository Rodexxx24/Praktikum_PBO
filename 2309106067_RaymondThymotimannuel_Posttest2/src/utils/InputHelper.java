package utils;
import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static String nextLine() {
        return scanner.nextLine();
    }

    public static int nextInt() {
        return scanner.nextInt();
    }

    public static double nextDouble() {
        return scanner.nextDouble();
    }
}
