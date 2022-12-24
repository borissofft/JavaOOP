package solid;

import solid.products.Food;

import java.util.List;

public class QuantityCalculator {

    private List<Food> foods;

    public QuantityCalculator(List<Food> foods) {
        this.foods = foods;
    }

    public double totalAmountKilograms() {
        double kilograms = 0;
        for (Food food : this.foods) {
            kilograms += food.calculateKilograms();
        }
        return kilograms;
    }

    public double averageValueKilograms() {
        return this.totalAmountKilograms() / this.foods.size();
    }

}
