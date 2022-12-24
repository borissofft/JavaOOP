package L06SOLID.Exercise.MyImplement.solid.products;

public class Coke implements Product, Drink, Food {

    private static final double CALORIES_PER_100_GRAMS = 44.0;
    private static final double DENSITY = 0.6;

    private double milliliters;

    public Coke(double milliliters) {
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return this.milliliters;
    }

    @Override
    public double calculateCalories() {
        double grams = getMilliliters() * DENSITY;
        return (CALORIES_PER_100_GRAMS / 100) * grams;
    }

    @Override
    public double calculateLiters() {
        return this.milliliters / 1000;
    }

    @Override
    public double calculateKilograms() {
        return this.calculateLiters() * DENSITY;
    }

}
