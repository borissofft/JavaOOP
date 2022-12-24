package L02Encapsulation.Exercise.P05FootballTeamGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, Team> teams = new LinkedHashMap<>();

        while (!"END".equals(input)) {

            String[] tokens = input.split(";");
            String command = tokens[0];
            String teamName = tokens[1];

            try {

                switch (command) {
                    case "Team":

                       Team team = new Team(teamName);
                        teams.put(teamName, team);
                        break;

                    case "Add": {

                        String playerName = tokens[2];
                        int endurance = Integer.parseInt(tokens[3]);
                        int sprint = Integer.parseInt(tokens[4]);
                        int dribble = Integer.parseInt(tokens[5]);
                        int passing = Integer.parseInt(tokens[6]);
                        int shooting = Integer.parseInt(tokens[7]);

                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            team = teams.get(teamName);
                            team.addPlayer(new Player(playerName, endurance, sprint, dribble, passing, shooting));
                        }
                        break;

                    }
                    case "Remove":

                        String playerName = tokens[2];
                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            team = teams.get(teamName);
                            team.removePlayer(playerName);
                        }
                        break;
                    case "Rating":

                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            team = teams.get(teamName);
                            double rating = team.getRating();
                            System.out.printf("%s - %d%n", team.getName(), Math.round(rating));
                        }
                        break;
                }

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            input = scanner.nextLine();

        }

    }
}
