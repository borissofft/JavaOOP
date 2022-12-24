package L05Polymorphism.Exercise.P02VehiclesExtension;

public class Truck extends Vehicle {

    private static final double ADDITIONAL_CONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, ADDITIONAL_CONSUMPTION, tankCapacity);
    }

    @Override
    public String refuel(double fuel) {
        double addFuel = super.getFuelQuantity() + fuel;
        String output = null;

        if (fuel <= 0) {
            output = "Fuel must be a positive number";
        } else if (addFuel > super.getTankCapacity()) {
            output = "Cannot fit fuel in tank";
        } else {
            super.refuel(fuel * 0.95);
        }
        return output;
    }

}
