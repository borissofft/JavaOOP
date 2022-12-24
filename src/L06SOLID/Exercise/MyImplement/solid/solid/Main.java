package L06SOLID.Exercise.MyImplement.solid.solid;

import L06SOLID.Exercise.MyImplement.solid.solid.products.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Product chocolate = new Chocolate(25);
        Product chips = new Chips(30);
        List<Product> products = new ArrayList<>();
        products.add(chocolate);
        products.add(chips);

        CalorieCalculator calorieCalculator = new CalorieCalculator(products);

        System.out.println(calorieCalculator.sum());
        System.out.println(calorieCalculator.average());

        Printer printer = new Printer();

        printer.printSum(calorieCalculator.sum());
        printer.printAverage(calorieCalculator.average());

        List<Food> foods = new ArrayList<>();
        Food coke = new Coke(50);
        Food lemonade = new Lemonade(100);
        foods.add(coke);
        foods.add(lemonade);
        QuantityCalculator quantityCalculator = new QuantityCalculator(foods);
        printer.totalAmountKilograms(quantityCalculator.totalAmountKilograms());
        printer.averageValueKilograms(quantityCalculator.averageValueKilograms());

    }
}
