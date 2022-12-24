package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {

    private Map<String, HealthyFood> entities;

    public HealthFoodRepositoryImpl() {
        this.entities = new LinkedHashMap<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return this.entities.get(name);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableCollection(this.entities.values());
    }

    @Override
    public void add(HealthyFood food) {
        this.entities.put(food.getName(), food);
    }

}
