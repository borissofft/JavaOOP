package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStates = 0;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }


    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer = null;
        switch (type) {
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        this.explorerRepository.add(explorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        Collection<String> exhibitsCollection = state.getExhibits();
//        if (exhibits.length == 1) {
//            String exhibit = exhibits[0];
//            exhibitsCollection.add(exhibit);
//        } else {
//            for (String exhibit : exhibits) {
//                exhibitsCollection.add(exhibit);
//            }
//        }
//        Collections.addAll(exhibitsCollection, exhibits);
        exhibitsCollection.addAll(Arrays.asList(exhibits));
        this.stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = this.explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        this.explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {

        List<Explorer> explorers = this.explorerRepository.getCollection()
                .stream().filter(explorer -> explorer.getEnergy() > 50)
                .collect(Collectors.toCollection(ArrayList::new));

        if (explorers.size() == 0) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = this.stateRepository.byName(stateName);

        Mission mission = new MissionImpl();
        mission.explore(state, explorers);
        this.exploredStates++;

        int retiredExplorers = (int) explorers.stream().filter(explorer -> explorer.getEnergy() == 0).count();

        return String.format(ConstantMessages.STATE_EXPLORER, stateName, retiredExplorers);
    }

    @Override
    public String finalResult() {

        StringBuilder sb = new StringBuilder();
        Collection<Explorer> explorers = this.explorerRepository.getCollection();
        String noSuitcase = null;

        sb.append(String.format("%d states were explored.", this.exploredStates)).append(System.lineSeparator());
        sb.append("Information for the explorers:").append(System.lineSeparator());

        for (Explorer explorer : explorers) {
            sb.append(String.format("Name: %s", explorer.getName())).append(System.lineSeparator());
            sb.append(String.format("Energy: %.0f", explorer.getEnergy())).append(System.lineSeparator());
            sb.append("Suitcase exhibits: ");
            if (explorer.getSuitcase().getExhibits().size() == 0) {
                noSuitcase = "None";
                sb.append(noSuitcase).append(System.lineSeparator());
            } else {
                sb.append(String.format("%s", String.join(", ", explorer.getSuitcase().getExhibits()))).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

}
