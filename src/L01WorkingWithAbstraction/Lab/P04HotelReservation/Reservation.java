package L01WorkingWithAbstraction.Lab.P04HotelReservation;

public class Reservation {

    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private Discount discount;

    public Reservation(double pricePerDay, int numberOfDays, Season season, Discount discount) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discount = discount;
    }

    public double getPricePerDay() {
        return this.pricePerDay;
    }

    public int getNumberOfDays() {
        return this.numberOfDays;
    }

    public Season getSeason() {
        return this.season;
    }

    public Discount getDiscount() {
        return this.discount;
    }

}
