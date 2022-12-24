package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {

    private static final double INITIAL_KILOGRAMS = 2.50;
    private static final String AREA = "WaterArea";

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KILOGRAMS, price);
    }

    // Can only live in WaterArea!

    @Override
    public void eat() {
        this.setKg(this.getKg() + 7.50);
    }


}
