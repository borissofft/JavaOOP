package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish {

    private static final int SIZE = 3;

    // Can only live in FreshwaterAquarium!

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
    }

    @Override
    protected void setSize(int size) {
        super.setSize(SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + 3);
    }

}
