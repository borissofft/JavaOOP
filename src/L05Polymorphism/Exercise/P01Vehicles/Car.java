package L05Polymorphism.Exercise.P01Vehicles;

public class Car extends Vehicle {

    private static final double ADDITIONAL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption, ADDITIONAL_CONSUMPTION);
    }

}
