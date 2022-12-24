package L03Inheritance.Lab.P05RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList {

    private static final Random RANDOM;

    static {
        RANDOM = new Random();
    }

    public Object getRandomElement() {
        int index = this.RANDOM.nextInt(super.size());
        Object object = super.remove(index);
        return object;
    }

}
