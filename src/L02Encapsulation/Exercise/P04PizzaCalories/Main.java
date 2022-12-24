package L02Encapsulation.Exercise.P04PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaData = scanner.nextLine().split("\\s+");
        String pizzaName = pizzaData[1];
        int numberOfToppings = Integer.parseInt(pizzaData[2]);

        String[] doughData = scanner.nextLine().split("\\s+");
        String flourType = doughData[1];
        String bakingTechnique = doughData[2];
        int weightInGrams = Integer.parseInt(doughData[3]);

        Pizza pizza = null;

        try {

            pizza = new Pizza(pizzaName, numberOfToppings);
            Dough dough = new Dough(flourType, bakingTechnique, weightInGrams);
            pizza.setDough(dough);


            String input = scanner.nextLine();

            while (!"END".equals(input) && pizza != null) {

                String[] toppingData = input.split("\\s+");
                String toppingType = toppingData[1];
                double weightTopping = Double.parseDouble(toppingData[2]);

                Topping topping = new Topping(toppingType, weightTopping);
                pizza.addTopping(topping);

                input = scanner.nextLine();
            }

        if (pizza != null) {
            System.out.println(pizza);
        }

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
