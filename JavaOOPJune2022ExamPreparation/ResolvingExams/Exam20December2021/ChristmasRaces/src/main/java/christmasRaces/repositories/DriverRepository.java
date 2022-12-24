package christmasRaces.repositories;

import christmasRaces.entities.drivers.Driver;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverRepository implements Repository<Driver> {

    private Map<String, Driver> models;

    public DriverRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Driver driver) {
        this.models.put(driver.getName(), driver);
    }

    @Override
    public boolean remove(Driver driver) {
        return this.models.remove(driver.getName()) != null;
    }

}
