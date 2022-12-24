package L03Inheritance.Exercise.P06Animals.animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Animal dog = new Dog("Sharo", 23, "Male");
        System.out.println(dog.produceSound());

        Cat cat  = new Kitten("cat", 45);
        System.out.println(cat.produceSound());

        System.out.println(cat.toString());

    }

}
