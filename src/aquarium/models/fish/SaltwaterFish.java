package aquarium.models.fish;

public class SaltwaterFish extends BaseFish {

    private static final int INITIAL_SIZE = 5;
    private static final int VALUE_TO_ADD = 2;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.setSize(INITIAL_SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + VALUE_TO_ADD);
    }
}
