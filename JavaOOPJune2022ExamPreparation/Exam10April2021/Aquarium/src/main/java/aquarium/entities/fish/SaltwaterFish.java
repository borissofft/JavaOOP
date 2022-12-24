package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish {

    private static final int SIZE = 5;

    // Can only live in SaltwaterAquarium!

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    protected void setSize(int size) {
        super.setSize(SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + 2);
    }

}
