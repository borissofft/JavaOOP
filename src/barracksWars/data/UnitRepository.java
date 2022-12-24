package barracksWars.data;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import jdk.jshell.spi.ExecutionControl;

import java.util.Map;
import java.util.TreeMap;

public class UnitRepository implements Repository {

    private Map<String, Integer> amountOfUnits;

    public UnitRepository() {
        this.amountOfUnits = new TreeMap<>();
    }

    public void addUnit(Unit unit) {
        String unitType = unit.getClass().getSimpleName();
        if (!this.amountOfUnits.containsKey(unitType)) {
            this.amountOfUnits.put(unitType, 0);
        }

        int newAmount = this.amountOfUnits.get(unitType) + 1;
        this.amountOfUnits.put(unitType, newAmount);
    }

    public String getStatistics() {
        StringBuilder statBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : amountOfUnits.entrySet()) {
            String formatedEntry =
                    String.format("%s -> %d%n", entry.getKey(), entry.getValue());
            statBuilder.append(formatedEntry);
        }
        statBuilder.setLength(
                statBuilder.length() - System.lineSeparator().length());

        return statBuilder.toString();
    }

//	public void removeUnit(String unitType) throws ExecutionControl.NotImplementedException {
//		// TODO: implement for problem 4
//		throw new ExecutionControl.NotImplementedException("message");
//	}

    public void removeUnit(String unitType) { // Тъй като метода е void, класа Retire, може да разбере какво се е случило в този метод единствено по call stack-а
        // TODO: implement for problem 4

        if (!this.amountOfUnits.containsKey(unitType) || this.amountOfUnits.get(unitType) == 0) {
            throw new IllegalArgumentException("No such units in repository.");
        }

        int oldCount = this.amountOfUnits.get(unitType);

        if (oldCount == 0) {
            this.amountOfUnits.remove(unitType);
        } else {
//            this.amountOfUnits.put(unitType, --oldCount);
            this.amountOfUnits.put(unitType, this.amountOfUnits.get(unitType) - 1);
        }

    }


}
