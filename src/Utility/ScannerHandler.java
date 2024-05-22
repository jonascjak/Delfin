package Utility;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerHandler {
    private static final Scanner scan = new Scanner(System.in);

    public static int scanInt() {
        while (true) {
            try {
                return scan.nextInt();
            } catch (InputMismatchException ignored) {
                System.out.println("Invalid input. Please try again.");
                scan.next();
            }
        }
    }

    public static String scanString() {
        while (true) {
            try {
                return scan.nextLine();
            } catch (InputMismatchException ignored) {
                System.out.println("Invalid input. Please try again.");
                scan.next();
            }
        }
    }

    public static double scanDouble(){
        while (true) {
            try {
                return scan.nextDouble();
            } catch (InputMismatchException ignored) {
                System.out.println("Invalid input. Please try again.");
                scan.next();
            }
        }
    }

    public static LocalDate scanDate(){
        while (true) {
            try {
                String stringFormatter = scan.next();
                return LocalDate.parse(stringFormatter);
            } catch (InputMismatchException ignored) {
                System.out.println("Invalid date format. Please enter a date in the format of YYYY-MM-DD");
                scan.next();
            }
        }
    }
}

