package L05Polymorphism.Exercise.P02VehiclesExtension;

import java.text.DecimalFormat;

public abstract class Vehicle {

//    private boolean withPeople = true;

    private double fuelQuantity;
    private boolean airConditionIsOn;
    private double fuelConsumption;
    private double additionalConsumption;
    private double tankCapacity;

    protected Vehicle(double fuelQuantity, double fuelConsumption, double additionalConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
//        this.setAirConditionIsOn();
        this.setAirConditionIsOn(true);
        this.additionalConsumption = additionalConsumption;
        this.setFuelConsumption(fuelConsumption);
        this.tankCapacity = tankCapacity;
    }

//    public void setWithPeople(boolean withPeople) {
//        this.withPeople = withPeople;
//    }

//    protected void setAirConditionIsOn() { // С този метод можем да сетваме дали климатика е включен или не според сезона...
//        if (this.withPeople) {
//            this.airConditionIsOn = true;
//        } else {
//            this.airConditionIsOn = false;
//        }
//    }


    public void setAirConditionIsOn(boolean airConditionIsOn) {
        this.airConditionIsOn = airConditionIsOn;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        if (airConditionIsOn) {
            this.fuelConsumption = fuelConsumption + this.additionalConsumption;
        } else {
            this.fuelConsumption = fuelConsumption;
        }
    }

    protected double getFuelQuantity() {
        return fuelQuantity;
    }

    protected double getTankCapacity() {
        return tankCapacity;
    }

    public String drive(double distance) { // Could be with try - catch
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

    public String refuel(double fuel) { // Could be with try - catch
        double addFuel = this.fuelQuantity + fuel;
        String output = null;

        if (fuel <= 0) {
            output = "Fuel must be a positive number";
        } else if (addFuel > this.tankCapacity) {
            output = "Cannot fit fuel in tank";
        } else {
            this.fuelQuantity += fuel;
        }
        return output;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }

}
