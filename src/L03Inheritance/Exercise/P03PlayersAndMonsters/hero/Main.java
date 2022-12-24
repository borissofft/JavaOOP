package L03Inheritance.Exercise.P03PlayersAndMonsters.hero;

//package hero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MuseElf museElf = new MuseElf("Toshko", 1);
        System.out.println(museElf.getUsername());
        System.out.println(museElf.toString());

    }
}
