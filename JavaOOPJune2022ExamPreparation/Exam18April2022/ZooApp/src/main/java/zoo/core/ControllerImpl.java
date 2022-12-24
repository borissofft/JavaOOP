package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    /**
     * Map<String, Area> areas - ако направим така колекцията от Area, ще можем много по-лесно да си взимаме Area-та по име
     */

    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {

//        boolean validAreas = ("WaterArea".equals(areaType) || "LandArea".equals(areaType));
//        if (!validAreas) {
//            throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
//        }

        Area area = null;
        switch (areaType) {
            case "WaterArea":
                area = new WaterArea(areaName);
                break;
            case "LandArea":
                area = new LandArea(areaName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }

        this.areas.add(area);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
//        boolean validFoodType = ("Vegetable".equals(foodType) || "Meat".equals(foodType));
//        if (!validFoodType) {
//            throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
//        }

        Food food = null;
        switch (foodType) {
            case "Vegetable":
                food = new Vegetable();
                break;
            case "Meat":
                food = new Meat();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }

        this.foodRepository.add(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Area area = getAreaByName(areaName);
        Food food = this.foodRepository.findByType(foodType);

        if (food == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));
        } else {
            area.addFood(food);
        }

        this.foodRepository.remove(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {

//        boolean validAnimalType = ("AquaticAnimal".equals(animalType) || "TerrestrialAnimal".equals(animalType));
//
//        if (!validAnimalType) {
//            throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
//        }

        Animal animal = null;

        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, kind, price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }

        Area area = getAreaByName(areaName);

        // "Not enough capacity." - if there is not enough capacity to add the Animal in the Area. - този exception се хвърля сам при опит за добавяне на животно
        // в колекцията от метода addAnimal(Animal animal) в абстрактният клас BaseArea

        if ("WaterArea".equals(area.getClass().getSimpleName()) && "AquaticAnimal".equals(animalType)) {
            area.addAnimal(animal);
        } else if ("LandArea".equals(area.getClass().getSimpleName()) && "TerrestrialAnimal".equals(animalType)) {
            area.addAnimal(animal);
        } else {
            return ConstantMessages.AREA_NOT_SUITABLE;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = getAreaByName(areaName);
        area.feed();
        return String.format(ConstantMessages.ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = getAreaByName(areaName);
        double kilograms = area.getAnimals().stream().mapToDouble(Animal::getKg).sum();
        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, kilograms);
    }

//    @Override
//    public String getStatistics() {
//        StringBuilder sb = new StringBuilder();
//        for (Area area : this.areas) {
//            sb.append(area.getInfo()).append(System.lineSeparator());
//        }
//        return sb.toString().trim();
//    }

    @Override
    public String getStatistics() {
        return areas.stream()
                .map(Area::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private Area getAreaByName(String areaName) {
        return this.areas.stream().filter(area1 -> area1.getName().equals(areaName)).findFirst().orElse(null);
    }

}
