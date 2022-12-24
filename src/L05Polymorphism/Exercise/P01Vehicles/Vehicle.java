package L05Polymorphism.Exercise.P01Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {

    private double fuelQuantity;
    private boolean airConditionIsOn;
    private double fuelConsumption;
    private double additionalConsumption;

    protected Vehicle(double fuelQuantity, double fuelConsumption, double additionalConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.setAirConditionIsOn();
        this.additionalConsumption = additionalConsumption;
        this.setFuelConsumption(fuelConsumption);
    }

    protected void setFuelConsumption(double fuelConsumption) {
        if (airConditionIsOn) {
            this.fuelConsumption = fuelConsumption + this.additionalConsumption;
        } else {
            this.fuelConsumption = fuelConsumption;
        }
    }

    protected void setAirConditionIsOn() { // С този метод можем да сетваме дали климатика е включен или не според сезона...
        this.airConditionIsOn = true;
    }

    public String drive(double distance) {
        String output = null;
        double neededFuel = distance * this.fuelConsumption;

        if (neededFuel <= this.fuelQuantity) {
            this.fuelQuantity -= neededFuel;
            DecimalFormat formatter = new DecimalFormat("#.##");
            output = String.format("%s travelled %s km", this.getClass().getSimpleName(), formatter.format(distance));
        } else {
            output = String.format("%s needs refueling", this.getClass().getSimpleName());
        }
        return output;
    }

    public void refuel(double fuel) {
        this.fuelQuantity += fuel;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }

}
