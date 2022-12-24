package L08ExceptionsAndErrorHandling;

import java.util.Scanner;

public class P02SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        try {
            int num = Integer.parseInt(scanner.nextLine());
            if (num < 0) {
                throw new ArithmeticException();
            }
            System.out.printf("%.2f%n", Math.sqrt(num));
        } catch (NumberFormatException | ArithmeticException ex) {
            System.out.println("Invalid");
        } finally {
            System.out.println("Goodbye");
        }

    }
}
