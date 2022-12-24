package solid.products;

public class Chocolate implements Product, Food {

    private static final double CALORIES_PER_100_GRAMS = 575.0;

    private double grams;

    public Chocolate(double grams) {
        this.grams = grams;
    }

    public double getGrams() {
        return this.grams;
    }

    @Override
    public double calculateCalories() {
        return (CALORIES_PER_100_GRAMS / 100) * getGrams();
    }

    @Override
    public double calculateKilograms() {
        return this.grams / 1000;
    }

}
