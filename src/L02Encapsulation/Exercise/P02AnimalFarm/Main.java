package L02Encapsulation.Exercise.P02AnimalFarm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());

        try {

            Chicken chicken = new Chicken(name, age);
            System.out.println(chicken);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        // Proba
//        Chicken ch = new Chicken("asd",12);
//        System.out.println(ch.productPerDay());

    }
}
