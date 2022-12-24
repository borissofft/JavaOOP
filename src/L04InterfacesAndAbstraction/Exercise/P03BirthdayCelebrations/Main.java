package L04InterfacesAndAbstraction.Exercise.P03BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Birthable> birthables = new ArrayList<>();

        while (!"End".equals(input)) {
            String[] data = input.split("\\s+");
            String type = data[0];

            if ("Citizen".equals(type)) {

                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String id = data[3];
                String birthDate = data[4];
                birthables.add(new Citizen(name, age, id, birthDate));

            } else if ("Pet".equals(type)) {

                String name = data[1];
                String birthDate = data[2];
                birthables.add(new Pet(name, birthDate));

            } else {

                String id = data[1];
                String model = data[2];

            }

            input = scanner.nextLine();
        }

        String year = scanner.nextLine();

        for (Birthable birthable : birthables) {
            if (birthable.getBirthDate().contains(year)) {
                System.out.println(birthable.getBirthDate());
            }
        }

    }


}
