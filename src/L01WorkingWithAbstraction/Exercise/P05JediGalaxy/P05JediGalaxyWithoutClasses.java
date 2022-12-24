package L01WorkingWithAbstraction.Exercise.P05JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class P05JediGalaxyWithoutClasses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readIntArray(scanner.nextLine());
        int row = dimensions[0];
        int col = dimensions[1];

        int[][] matrix = new int[row][col];

        int value = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = value++;
            }
        }

        String command = scanner.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] playerPosition = readIntArray(command);

            int playerRow = playerPosition[0];
            int playerCol = playerPosition[1];

            command = scanner.nextLine();

            int[] evilPosition = readIntArray(command);
            int evilRow = evilPosition[0];
            int evilCol = evilPosition[1];

            while (evilRow >= 0 && evilCol >= 0) {
                if (evilRow < matrix.length && evilCol < matrix[0].length) {
                    matrix[evilRow][evilCol] = 0;
                }
                evilRow--;
                evilCol--;
            }

            while (playerRow >= 0 && playerCol < matrix[1].length) {
                if (playerRow < matrix.length && playerCol >= 0 && playerCol < matrix[0].length) {
                    sum += matrix[playerRow][playerCol];
                }

                playerCol++;
                playerRow--;
            }

            command = scanner.nextLine();
        }

        System.out.println(sum);


    }

    private static int[] readIntArray(String line) {
        int[] array = Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        return array;
    }

}
