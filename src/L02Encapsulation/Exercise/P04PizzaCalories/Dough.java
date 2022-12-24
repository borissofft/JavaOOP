package L02Encapsulation.Exercise.P04PizzaCalories;

public class Dough {

    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        Validator.validateFlourType(flourType);
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        Validator.validateBakingTechnique(bakingTechnique);
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        Validator.validateDoughWeight(weight);
        this.weight = weight;
    }

    public double calculateCalories() {
        double calories = this.weight * 2;

        if ("White".equals(this.flourType)) {
            calories *= 1.5;
        }

        if ("Crispy".equals(this.bakingTechnique)) {
            calories *= 0.9;
        } else if ("Chewy".equals(this.bakingTechnique)) {
            calories *= 1.1;
        }

        return calories;
    }

}
