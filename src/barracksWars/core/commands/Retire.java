package barracksWars.core.commands;

import barracksWars.anotations.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class Retire extends Command {

    /**
     * Task 4
     */

//    protected Retire(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }
//
//    @Override
//    public String execute() {
//
//        String unitType = this.getData()[1];
//        String result = null;
//
//        /**
//         * с try/catch блока, използваме call stack-а, за да видим дали void метода removeUnit(String unitType) в UnitRepository е върнал exception.
//         */
//
//        try {
//            this.getRepository().removeUnit(unitType);
//            result = String.format("%s retired!", unitType);
//        } catch (IllegalArgumentException ex) {
//            result = ex.getMessage();
//        }
//
//        return result;
//    }

    /**
     * Task 5 dependency injection
     */

    @Inject
    private Repository repository;

    protected Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() {

        String unitType = this.getData()[1];
        String result = null;

        /**
         * с try/catch блока, използваме call stack-а, за да видим дали void метода removeUnit(String unitType) в UnitRepository е върнал exception.
         */

        try {
            this.repository.removeUnit(unitType);
            result = String.format("%s retired!", unitType);
        } catch (IllegalArgumentException ex) {
            result = ex.getMessage();
        }

        return result;
    }

}
