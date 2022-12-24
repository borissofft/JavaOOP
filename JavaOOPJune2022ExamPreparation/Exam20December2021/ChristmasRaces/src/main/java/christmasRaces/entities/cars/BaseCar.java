package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car {

    private static final int MIN_MODEL_NAME_LENGTH = 4;

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.setCubicCentimeters(cubicCentimeters);
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < MIN_MODEL_NAME_LENGTH) {
            String exceptionMessage = String.format(ExceptionMessages.INVALID_MODEL, model, MIN_MODEL_NAME_LENGTH);
            throw new IllegalArgumentException(exceptionMessage);
        }
        this.model = model;
    }

    // Variant 1

    protected abstract void checkHorsePower(int horsePower);

    private void setHorsePower(int horsePower) { // При създаване на нова инстанция от Child класовете, винаги ще се минава през този сетър, който посредством
        this.checkHorsePower(horsePower);// абстрактният метод checkHorsePower(int horsePower), ще валидира данните за horsePower, за всяка отделна кола
        this.horsePower = horsePower;
    }

    // Variant 2

//    protected void setHorsePower(int horsePower) {
//        this.horsePower = horsePower;
//    }

    private void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / this.horsePower * laps;
    }

}
