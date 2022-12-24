package L05Polymorphism.Exercise.P03WildFarm.animals;

//package animals;

import L05Polymorphism.Exercise.P03WildFarm.foods.Food;

//import foods.Food;

import java.text.DecimalFormat;

public abstract class Animal {

    private String animalName;
    private String animalType;
    private Double animalWeight;
    private Integer foodEaten;

    protected Animal(String animalName, String animalType, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    public String getAnimalType() {
        return this.animalType;
    }


    public abstract void makeSound();

    public void eatFood(Food food) {
        this.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {

        DecimalFormat formatter = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, region, %d]"
                , this.animalType
                , this.animalName
                , formatter.format(this.animalWeight)
                , this.foodEaten);

    }
}
