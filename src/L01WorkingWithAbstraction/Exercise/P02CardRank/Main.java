package L01WorkingWithAbstraction.Exercise.P02CardRank;

public class Main {
    public static void main(String[] args) {

        CardRank[] cards = CardRank.values();

        System.out.println("Card Ranks:");
        for (CardRank card : cards) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", card.ordinal(), card);
        }

    }
}
