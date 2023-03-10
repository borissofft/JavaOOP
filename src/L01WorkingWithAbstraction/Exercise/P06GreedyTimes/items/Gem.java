package L01WorkingWithAbstraction.Exercise.P06GreedyTimes.items;

public class Gem extends Item {

    private String name;

    public Gem(String name, long quantity) {
        super(quantity);
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("##%s - %d", this.name, this.getQuantity());
    }

}
