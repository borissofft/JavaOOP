package L04InterfacesAndAbstraction.Exercise.P04FoodShortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Buyer> people = new ArrayList<>();
        while (n-- > 0) {

            String[] data = scanner.nextLine().split("\\s+");
            String name = data[0];
            int age = Integer.parseInt(data[1]);

            if (data.length == 4) {

                String id = data[2];
                String birthDate = data[3];
                people.add(new Citizen(name, age, id, birthDate));

            } else {

                String group = data[2];
                people.add(new Rebel(name, age, group));

            }

        }

        String name = scanner.nextLine();
        while (!"End".equals(name)) {

            for (Buyer person : people) {
                if (person.getName().equals(name)) {
                    person.buyFood();
                    break;
                }
            }

            name = scanner.nextLine();

        }

        int purchasedFood = 0;
        for (Buyer person : people) {
            purchasedFood += person.getFood();
        }

        System.out.println(purchasedFood);

    }
}
