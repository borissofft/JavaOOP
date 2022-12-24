package L04InterfacesAndAbstraction.Exercise.P04FoodShortage;

public class Rebel implements Person, Buyer {

    private static final int INITIAL_FOOD = 0;

    private String name;
    private int age;
    private String group;
    private int food;

    public Rebel(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
        this.food = INITIAL_FOOD;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    public String getGroup() {
        return this.group;
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public void buyFood() {
        this.food += 5;
    }

    @Override
    public String toString() {
        return "Rebel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", group='" + group + '\'' +
                ", food=" + food +
                '}';
    }
}
