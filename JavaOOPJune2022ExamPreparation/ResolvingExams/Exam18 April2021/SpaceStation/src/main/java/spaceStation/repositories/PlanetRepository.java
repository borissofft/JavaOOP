package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlanetRepository implements Repository<Planet> { // Repository for planets that await to be explored.

    private Map<String, Planet> planets;

    public PlanetRepository() {
        this.planets = new LinkedHashMap<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return Collections.unmodifiableCollection(this.planets.values());
    }

    @Override
    public void add(Planet planet) {
        this.planets.put(planet.getName(), planet);
    }

    @Override
    public boolean remove(Planet planet) {
        return this.planets.remove(planet.getName()) != null;
    }

    @Override
    public Planet findByName(String name) {
        return this.planets.get(name); // It is guaranteed that the planet exists in the collection.
    }

}
