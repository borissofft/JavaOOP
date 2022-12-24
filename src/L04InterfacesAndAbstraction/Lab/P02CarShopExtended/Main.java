package L04InterfacesAndAbstraction.Lab.P02CarShopExtended;

public class Main {
    public static void main(String[] args) {

        Car seat = new Seat("Leon", "Gray", 110, "Spain", 11111.1); // не трябва да бъде Sellable seat = new Seat()
        Car audi = new Audi("A4", "Gray", 110, "Germany", 3, 99.9); // не трябва да бъде Rentable audi = new Audi

        printCarInfo(seat);
        printCarInfo(audi);
    }

    private static void printCarInfo(Car car) {
        System.out.println(String.format(
                "%s is %s color and have %s horse power",
                car.getModel(),
                car.getColor(),
                car.getHorsePower()));
        System.out.println(car.toString());

    }

}
