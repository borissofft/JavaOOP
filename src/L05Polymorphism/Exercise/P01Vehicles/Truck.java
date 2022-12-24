package L05Polymorphism.Exercise.P01Vehicles;

public class Truck extends Vehicle {

    private static final double ADDITIONAL_CONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption, ADDITIONAL_CONSUMPTION);
    }

    @Override
    public void refuel(double fuel) {
        super.refuel(fuel * 0.95);
    }

}
