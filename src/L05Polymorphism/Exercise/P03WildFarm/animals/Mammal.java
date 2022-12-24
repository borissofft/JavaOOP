package L05Polymorphism.Exercise.P03WildFarm.animals;

//package animals;

import L05Polymorphism.Exercise.P03WildFarm.foods.Food;

//import foods.Food;

public abstract class Mammal extends Animal {

    private String livingRegion;

    protected Mammal(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight);
        this.livingRegion = livingRegion;
    }

    @Override
    public void eatFood(Food food) {

        boolean foodIsMeat = "Meat".equals(food.getClass().getSimpleName());

        if (foodIsMeat && !(this instanceof Felime)) {

            String message = (this.getAnimalType() + "s are not eating that type of food!")
                    .replace("Mouses", "Mice");
            throw new IllegalArgumentException(message);

        } else if (!foodIsMeat && "Tiger".equals(this.getAnimalType())) {
            throw new IllegalArgumentException("Tigers are not eating that type of food!");
        }

        super.eatFood(food);

    }

        @Override
    public String toString() {
        return super.toString().replace("region", this.livingRegion);
    }


}
