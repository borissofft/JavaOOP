package L01WorkingWithAbstraction.Lab;

import java.util.Scanner;

public class P01RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int row = 1; row <= n; row++) {
            printCurrentRow(n, row);
        }

        for (int row = n - 1; row > 0; row--) {
            printCurrentRow(n, row);
        }

    }

    private static void printCurrentRow(int n, int row) {
        System.out.print(printSpace(" ", n - row));
        System.out.print(printSpace("* ", row));
        System.out.println();
    }

    private static String printSpace(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < count; j++) {
            sb.append(str);
        }
        return sb.toString();
    }


}
