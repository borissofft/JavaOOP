package L05Polymorphism.Exercise.P03WildFarm.animals;

//package animals;

import L05Polymorphism.Exercise.P03WildFarm.foods.Food;

//import foods.Food;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String input = scanner.nextLine();
        while (!"End".equals(input)) {

            String[] animalData = input.split("\\s+");
            Animal animal = produceAnimal(animalData);

            String[] foodData = scanner.nextLine().split("\\s+");
            Food food = produceFood(foodData);

            animal.makeSound();

            try {
                animal.eatFood(food);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            animals.add(animal);

            input = scanner.nextLine();

        }

        animals.forEach(System.out::println);

    }


    private static Animal produceAnimal(String[] animalData) {

        String animalType = animalData[0];
        String animalName = animalData[1];
        Double animalWeight = Double.parseDouble(animalData[2]);
        String animalLivingRegion = animalData[3];

        Animal animal = null;

        if ("Mouse".equals(animalType)) {
            animal = new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
        } else if ("Zebra".equals(animalType)) {
            animal = new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
        } else if ("Cat".equals(animalType)) {
            String catBreed = animalData[4];
            animal = new Cat(animalName, animalType, animalWeight, animalLivingRegion, catBreed);
        } else if ("Tiger".equals(animalType)) {
            animal = new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
        }

        return animal;
    }

    private static Food produceFood(String[] foodData) {
        String foodType = foodData[0];
        Integer quantity = Integer.parseInt(foodData[1]);

        Food food = null;

        try {
            Class foodClass = Class.forName("L05Polymorphism.Exercise.P03WildFarm.foods." + foodType); // Reflection
//            Class foodClass = Class.forName("foods." + foodType); // Reflection
            Constructor constructor = foodClass.getConstructor(Integer.class); // Вика конструктора на Food с аргумент Integer quantity
            food = (Food) constructor.newInstance(quantity);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return food;

    }

}
