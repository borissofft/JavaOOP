package barracksWars.core.commands;

import barracksWars.anotations.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;

public class Add extends Command { // Получава достъп до абстрактния конструктор и всички методи от абстрактния клас, како може и да имплементира(@Override) execute() командата

    /**
     * Task 4
     */

//    protected Add(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }
//
//    @Override
//    public String execute() {
//        String unitType = this.getData()[1];
//        Unit unitToAdd = this.getUnitFactory().createUnit(unitType);
//        this.getRepository().addUnit(unitToAdd);
//        return unitType + " added!";
//    }

    /**
     * Task 5 dependency injection
     */

    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public Add(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        return unitType + " added!";
    }

}
