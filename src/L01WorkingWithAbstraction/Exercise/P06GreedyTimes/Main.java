package L01WorkingWithAbstraction.Exercise.P06GreedyTimes;

import L01WorkingWithAbstraction.Exercise.P06GreedyTimes.bags.Bag;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] items = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(bagCapacity);

        for (int i = 0; i < items.length; i += 2) {
            String type = items[i];
            long quantity = Long.parseLong(items[i + 1]);

            if (type.length() == 3) {
                type = "Cash";
            } else if (type.toLowerCase().endsWith("gem")) {
                type = "Gem";
            } else if (type.toLowerCase().equals("gold")) {
                type = "Gold";
            }

            String name = items[i];


            if (bag.getCapacity() - quantity >= 0) {

                if ("Gem".equals(type)) {

                    bag.addGem(name, quantity);

                } else if ("Cash".equals(type)) {

                    bag.addCash(name, quantity);

                } else if ("Gold".equals(type)) {

                    bag.addGold(quantity);
                }

            }

        }

        System.out.println(bag.toString());

    }

}

// No sort implement !!! It's not submit in Judge