package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Map<Integer, Table> entities;

    public TableRepositoryImpl() {
        this.entities = new LinkedHashMap<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(this.entities.values());
    }

    @Override
    public void add(Table table) {
        this.entities.put(table.getTableNumber(), table);
    }

    @Override
    public Table byNumber(int number) {
        return this.entities.get(number);
    }

}
