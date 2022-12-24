package L01WorkingWithAbstraction.Exercise.P06GreedyTimes;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class P06GreedyTimesWithoutClasses {

    private static long capacityTaken = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, LinkedHashMap<String, Long>> bag = new LinkedHashMap<>();
        bag.put("Gold", new LinkedHashMap<>());
        bag.put("Gem", new LinkedHashMap<>());
        bag.put("Cash", new LinkedHashMap<>());

        long gold = 0;
        long gems = 0;
        long cash = 0;

        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] items = scanner.nextLine().split("\\s+");

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

            if (bagCapacity >= capacityTaken + quantity) {

                if ("Gem".equals(type) && gold >= gems + quantity) {

                    gems += quantity;
                    addStolenItem(bag, type, name, quantity);

                } else if ("Cash".equals(type) && gems >= cash + quantity) {

                    cash += quantity;
                    addStolenItem(bag, type, name, quantity);

                } else if ("Gold".equals(type)){

                    gold += quantity;
                    addStolenItem(bag, type, name, quantity);

                }

            }

        }

        for (var x : bag.entrySet()) {
            if (x.getValue().size() != 0) {
                Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

                System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

                x.getValue().entrySet()
                        .stream()
                        .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                        .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
            }
        }
    }

    private static void addStolenItem(LinkedHashMap<String, LinkedHashMap<String, Long>> bag, String type, String name, long quantity) {
        if (!bag.get(type).containsKey(name)) {
            bag.get(type).put(name, quantity);
        } else {
            bag.get(type).put(name, bag.get(type).get(name) + quantity);
        }
        capacityTaken += quantity;
    }
}