package L12DesignPatterns.Lab.builder;

public class Main {
    public static void main(String[] args) {

        // Позволява ни да създаваме комплексни обекти, който имат повече на брой конструктори

//        LunchOrder.Builder builder = LunchOrder.Builder.get(); // Builder обект. Спрямо него ще можем да зададем и полетата в LunchOrder

        LunchOrder order = LunchOrder.Builder
                .get()
                .withMeat("Chicken")
                .withDrink("Water")
                .withBread("White")
                .build();

        System.out.println(order);

    }
}
