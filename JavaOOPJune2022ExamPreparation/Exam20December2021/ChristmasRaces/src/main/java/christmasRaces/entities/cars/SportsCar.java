package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar {

    private static final double DEFAULT_CUBIC_CENTIMETERS = 3000;
    private static final int MIN_HORSE_POWER = 250;
    private static final int MAX_HORSE_POWER = 450;


    public SportsCar(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }

    // Variant 1

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER) {
            String exceptionMessage = String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    // Variant 2

//    @Override
//    protected void setHorsePower(int horsePower) {
//        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER) {
//            String exceptionMessage = String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower);
//            throw new IllegalArgumentException(exceptionMessage);
//        }
//        super.setHorsePower(horsePower);
//    }

}
