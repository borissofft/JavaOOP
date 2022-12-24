package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarRepository implements Repository<Car> {

    private Map<String, Car> cars;

    public CarRepository() {
        this.cars = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return this.cars.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.cars.values());
    }

    @Override
    public void add(Car car) {
        this.cars.putIfAbsent(car.getModel(), car);
    }

    @Override
    public boolean remove(Car car) {
        return this.cars.remove(car.getModel()) != null;
    }

}
