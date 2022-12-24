package L01WorkingWithAbstraction.Exercise.P03CardsWithPower;

public class Card {

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int calculatePower() {
        return this.rank.getValue() + this.suit.getValue();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", this.rank, this.suit, calculatePower());
    }

}
