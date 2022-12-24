package L05Polymorphism.Lab.P03Animals;

public class Cat extends Animal {

    private static final String MEEOW = "MEEOW";

    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        return super.baseExplain() + System.lineSeparator() + MEEOW;
    }

}
