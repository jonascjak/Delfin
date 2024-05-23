package Utility;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerHandler {
    private static final Scanner scan = new Scanner(System.in);

    public static int scanInt(int min, int max) {
        int value;
        while (true) {
            if (scan.hasNextInt()) {
                value = scan.nextInt();
                scan.nextLine();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again");
                }
            } else {
                System.out.println("Invalid input. Please try again");
                scan.next();
            }
        }
        return value;
    }

    public static String scanString() {
        while (true) {
            try {
                return scan.nextLine();
            } catch (InputMismatchException ignored) {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine();
            }
        }
    }

    public static double scanDouble(double min, double max) {
        double value;
        while (true) {
            if (scan.hasNextDouble()) {
                value = scan.nextDouble();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again");
                }
            } else {
                System.out.println("Invalid input. Please try again");
                scan.next();
            }
        }
        return value;
    }

    public static LocalDate scanDate(){
        while (true) {
            try {
                String stringFormatter = scan.nextLine();
                return LocalDate.parse(stringFormatter);
            } catch (InputMismatchException ignored) {
                System.out.println("Invalid date format. Please enter a date in the format of YYYY-MM-DD");
                scan.next();
            }
        }
    }

    public static void clearScanner(){
        scan.nextLine();
    }
}

