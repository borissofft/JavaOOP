package L01WorkingWithAbstraction.Lab.P04HotelReservation;

public enum Discount {

    VIP(20),
    SECONDVISIT(10),
    NONE(0);

    private int percentDiscount;

    Discount(int percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public int getPercentDiscount() {
        return this.percentDiscount;
    }

}
