package L01WorkingWithAbstraction.Exercise.P06GreedyTimes.bags;

import L01WorkingWithAbstraction.Exercise.P06GreedyTimes.items.Cash;
import L01WorkingWithAbstraction.Exercise.P06GreedyTimes.items.Gem;
import L01WorkingWithAbstraction.Exercise.P06GreedyTimes.items.Gold;
import L01WorkingWithAbstraction.Exercise.P06GreedyTimes.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private long capacity;
    private List<List<Item>> items;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.items.add(new ArrayList<>()); // index 0 -> Gem
        this.items.add(new ArrayList<>()); // index 1 -> Cash
        this.items.add(new ArrayList<>()); // index 2 -> Gold
    }

    public long getCapacity() {
        return this.capacity;
    }


    public void addGem(String name, long quantity) {
//        long goldAmount = this.getGoldAmount();
//        long gemsAmount = this.getGemAmount();

        if (this.getItemAmount(0) >= this.getItemAmount(1) + quantity) {
            this.capacity -= quantity;
            Item item = new Gem(name, quantity);
            this.items.get(1).add(item);
        }
    }

    public void addCash(String name, long quantity) {
        if (this.getItemAmount(1) >= this.getItemAmount(2) + quantity) {
            this.capacity -= quantity;
            Item item = new Cash(name, quantity);
            this.items.get(2).add(item);
        }
    }

    public void addGold(long quantity) {
        if (this.items.get(0).size() == 0) { // if nothing added
            this.items.get(0).add(new Gold(quantity));
        } else {
            this.items.get(0).get(0).addQuantity(quantity);
        }
    }


    private long getItemAmount(int index) {
        return this.items.get(index)
                .stream()
                .mapToLong(Item::getQuantity)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.items.get(0).size() != 0) {
            sb.append(this.items.get(0).get(0).toString()).append(System.lineSeparator());

            if (this.getItemAmount(0) != 0) {
                sb.append("##Gold - ").append(this.getItemAmount(0)).append(System.lineSeparator());
            }
        }


        if (this.items.get(1).size() != 0) {
            long gemsAmount = this.getItemAmount(1);

            sb.append(String.format("<Gem> $%d", gemsAmount)).append(System.lineSeparator());

            if (gemsAmount != 0) {
                this.items.get(1).stream()
                        .sorted().forEach(item -> {
                    sb.append(item.toString()).append(System.lineSeparator());
                });
            }
        }

        if (this.items.get(2).size() != 0) {
            long cashAmount = this.getItemAmount(2);

            sb.append(String.format("<Cash> $%d", cashAmount)).append(System.lineSeparator());

            if (cashAmount != 0) {
                this.items.get(2).stream()
                        .sorted().forEach(item -> {
                    sb.append(item.toString()).append(System.lineSeparator());
                });
            }
        }
        return sb.toString();
    }

    //    private long getCashAmount() {
//        return this.items.get(2)
//                .stream()
//                .mapToLong(Item::getQuantity)
//                .sum();
//    }
//
//    private long getGemAmount() {
//        return this.items.get(1)
//                .stream()
//                .mapToLong(Item::getQuantity)
//                .sum();
//    }
//
//    private long getGoldAmount() {
//        return this.items.get(0).get(0).getQuantity();
//    }

}
