package L01WorkingWithAbstraction.Exercise.P01CardSuit;

public class Main {
    public static void main(String[] args) {

    CardSuit[] cards = CardSuit.values();

        System.out.println("Card Suits:");
        for (CardSuit card : cards) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", card.ordinal(), card);
        }

    }
}
