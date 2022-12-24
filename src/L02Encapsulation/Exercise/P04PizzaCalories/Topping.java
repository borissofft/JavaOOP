package L02Encapsulation.Exercise.P04PizzaCalories;

public class Topping {

    /**
     * Using enums instead String
     */

//    private String toppingType;
    private ToppingTypes toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        Validator.validateToppingType(toppingType);
//        this.toppingType = toppingType;
        this.toppingType = ToppingTypes.valueOf(toppingType);
    }


    private void setWeight(double weight) {
//        Validator.validateToppingWeight(this.toppingType, weight);
        Validator.validateToppingWeight(this.toppingType.name(), weight);
        this.weight = weight;
    }

    public double calculateCalories() {
//        double calories = this.weight * 2;
//
//        switch (this.toppingType) {
//            case "Meat":
//                calories *= 1.2;
//                break;
//            case "Veggies":
//                calories *= 0.8;
//                break;
//            case "Cheese":
//                calories *= 1.1;
//                break;
//            case "Sauce":
//                calories *= 0.9;
//                break;
//        }
//        return calories;

        return this.weight * 2 * this.toppingType.getModifier();
    }

}
