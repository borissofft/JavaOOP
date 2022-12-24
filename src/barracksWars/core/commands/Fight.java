package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class Fight extends Command {

    /**
     * Task 4
     */

//    protected Fight(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }
//
//    @Override
//    public String execute() {
////        return "fight";
//        return this.getData()[0];
//    }

    /**
     * Task 5 dependency injection
     */

    public Fight(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
//        return "fight";
        return this.getData()[0];
    }

}
