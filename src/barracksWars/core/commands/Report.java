package barracksWars.core.commands;

import barracksWars.anotations.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

class Report extends Command {

    /**
     * Task 4
     */

//    private Report(String[] data, Repository repository, UnitFactory unitFactory) {
//        super(data, repository, unitFactory);
//    }
//
//    @Override
//    public String execute() {
//        return this.getRepository().getStatistics();
//    }

    /**
     * Task 5 dependency injection
     */

    @Inject
    private Repository repository;

    public Report(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }

}
