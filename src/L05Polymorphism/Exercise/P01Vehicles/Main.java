package L05Polymorphism.Exercise.P01Vehicles;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vehicle car = produceVehicle(scanner.nextLine().split("\\s+"));
        Vehicle truck = produceVehicle(scanner.nextLine().split("\\s+"));

        Map<String, Vehicle> vehicles = new HashMap<>();
        vehicles.put("Car", car);
        vehicles.put("Truck", truck);

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {

            String[] tokens = scanner.nextLine().split("\\s+");

            String output = null;
            if ("Drive".equals(tokens[0])) {
                output = vehicles.get(tokens[1]).drive(Double.parseDouble(tokens[2]));
            } else {
                vehicles.get(tokens[1]).refuel(Double.parseDouble(tokens[2]));
            }

            if (output != null) {
                System.out.println(output);
            }

        }

        vehicles.entrySet().forEach(entry -> System.out.println(entry.getValue()));

    }

    public static Vehicle produceVehicle(String[] data) {

        double fuelQuantity = Double.parseDouble(data[1]);
        double litersPerKm = Double.parseDouble(data[2]);
        Vehicle vehicle = null;

        if ("Car".equals(data[0])) {
            vehicle = new Car(fuelQuantity, litersPerKm);
        } else if ("Truck".equals(data[0])) {
            vehicle = new Truck(fuelQuantity, litersPerKm);
        }
        return vehicle;
    }

}
