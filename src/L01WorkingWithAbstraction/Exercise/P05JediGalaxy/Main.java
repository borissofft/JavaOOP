package L01WorkingWithAbstraction.Exercise.P05JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readIntArray(scanner.nextLine());
        int row = dimensions[0];
        int col = dimensions[1];

        Galactic galactic = new Galactic(dimensions[0], dimensions[1]);

        Player player = new Player();

        GalacticCluster cluster = new GalacticCluster(galactic);

        String command = scanner.nextLine();

        while (!command.equals("Let the Force be with you")) {
            int[] playerPosition = readIntArray(command);

            int playerRow = playerPosition[0];
            int playerCol = playerPosition[1];

            command = scanner.nextLine();

            int[] evilPosition = readIntArray(command);
            int evilRow = evilPosition[0];
            int evilCol = evilPosition[1];

            cluster.moveEvilForce(evilRow, evilCol);

            long starsToAdd = cluster.collectPlayerStars(playerRow, playerCol);
            player.addStars(starsToAdd);

            command = scanner.nextLine();
        }

        System.out.println(player.getStars());


    }

    private static int[] readIntArray(String line) {
        int[] array = Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        return array;
    }

}
