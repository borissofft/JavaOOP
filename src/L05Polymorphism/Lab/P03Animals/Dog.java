package L05Polymorphism.Lab.P03Animals;

public class Dog extends Animal {

    private static final String DJAAF = "DJAAF";

    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        return super.baseExplain() + System.lineSeparator() + DJAAF;
    }

}
