package L01WorkingWithAbstraction.Lab.P04HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s");

        double price = Double.parseDouble(input[0]);
        int days = Integer.parseInt(input[1]);
        Season season = Season.valueOf(input[2].toUpperCase());
        Discount discount = Discount.valueOf(input[3].toUpperCase());

        Reservation reservation = new Reservation(price, days, season, discount);

        double finalPrice = PriceCalculator.calculate(reservation);

        System.out.println(String.format("%.2f", finalPrice));

    }

}
