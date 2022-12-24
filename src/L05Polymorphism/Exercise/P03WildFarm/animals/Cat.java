package L05Polymorphism.Exercise.P03WildFarm.animals;

//package animals;

public class Cat extends Felime {

    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {

        String baseToString = super.toString();
        int indexFirstComa = baseToString.indexOf(",");

        StringBuilder sb = new StringBuilder(baseToString);
        sb.insert(indexFirstComa + 2, this.breed + ", ");
        return sb.toString();

    }


}
