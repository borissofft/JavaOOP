package zoo.entities.animals;

public class TerrestrialAnimal extends  BaseAnimal {

    private static final double INITIAL_KILOGRAMS = 5.50;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KILOGRAMS, price);
    }

    // Can only live in LandArea!

    @Override
    public void eat() {
        this.setKg(this.getKg() + 5.70);
    }

}
