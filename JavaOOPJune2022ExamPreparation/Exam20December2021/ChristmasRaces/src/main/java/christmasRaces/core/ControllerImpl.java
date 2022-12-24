package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private static final int MIN_DRIVERS_IN_RACE = 3;

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;


    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {

//        for (Driver driver : this.driverRepository.getAll()) {
//            if (driver.getName().equals(driverName)) {
//                throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
//            }
//        }

        if (this.driverRepository.getByName(driverName) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }

        Driver driver = new DriverImpl(driverName);
        this.driverRepository.add(driver);
        return String.format(OutputMessages.DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {

//        if (this.carRepository.getAll().contains(car)) {
//            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, car.getModel()));
//        }

        if (this.carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }

        Car car = null;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
        }

        this.carRepository.add(car);

        return String.format(OutputMessages.CAR_CREATED, type + "Car", model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {

        Driver driver = this.driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        Car car = this.carRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);

        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        Driver driver = this.driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);

        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {

        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        Collection<Driver> drivers = race.getDrivers();

        if (drivers.size() < MIN_DRIVERS_IN_RACE) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, MIN_DRIVERS_IN_RACE));
        }

//        int numberOfLaps = race.getLaps();
//        List<Driver> winners = drivers
//                .stream()
//                .sorted((f, s) -> (int) (s.getCar().calculateRacePoints(numberOfLaps) - f.getCar().calculateRacePoints(numberOfLaps)))
//                .limit(3).collect(Collectors.toList());

//        List<Driver> winners = drivers.stream().sorted((f, s) -> {
//            double first = f.getCar().calculateRacePoints(race.getLaps());
//            double second = s.getCar().calculateRacePoints(race.getLaps());
//            return Double.compare(second, first);
//        }).limit(3).collect(Collectors.toList());

        List<Driver> winners = drivers
                .stream()
                .sorted((f, s) -> Double.compare(s.getCar().calculateRacePoints(race.getLaps()), f.getCar().calculateRacePoints(race.getLaps())))
                .limit(3).collect(Collectors.toList());

        this.raceRepository.remove(race);

        Driver firstDriver = winners.get(0);
        Driver secondDriver = winners.get(1);
        Driver thirdDriver = winners.get(2);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, firstDriver.getName(), raceName)).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_SECOND_POSITION, secondDriver.getName(), raceName)).append(System.lineSeparator());
//        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, thirdDriver.getName(), raceName)).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, thirdDriver.getName(), raceName));

//        return sb.toString().trim(); // Не забравяй да тримваш output-а или да не слагаш нов ред на последния апенднат текст
        return sb.toString();
    }

    @Override
    public String createRace(String name, int laps) {

        Race race = this.raceRepository.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }

        Race newRace = new RaceImpl(name, laps);
        this.raceRepository.add(newRace);

        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
