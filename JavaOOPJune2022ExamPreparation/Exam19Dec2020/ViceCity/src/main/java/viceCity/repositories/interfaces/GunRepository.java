package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {

    private Map<String, Gun> models; // The gun repository holds information about a player's guns.

    public GunRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Gun gun) {
        this.models.putIfAbsent(gun.getName(), gun);
    }

    @Override
    public boolean remove(Gun gun) {
        return this.models.remove(gun.getName()) != null;
    }

    @Override
    public Gun find(String name) {
        return this.models.get(name);
    }

}
