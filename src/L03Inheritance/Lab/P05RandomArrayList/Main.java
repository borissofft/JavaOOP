package L03Inheritance.Lab.P05RandomArrayList;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        RandomArrayList randomArrayList = new RandomArrayList();

        randomArrayList.add("asd");
        randomArrayList.add(5);
        randomArrayList.add(new BigDecimal("3.4"));

        System.out.println(randomArrayList.getRandomElement());
        System.out.println(randomArrayList.getRandomElement());
        System.out.println(randomArrayList.getRandomElement());

    }
}
