package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;
import java.util.Collection;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        Collection<String> stateExhibits = state.getExhibits(); // Moje s ArrayList ili ArrayDeque!!!
        for (Explorer explorer : explorers) {
            while (explorer.canSearch() && stateExhibits.iterator().hasNext()) {
                String currentExhibit = stateExhibits.iterator().next();
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                stateExhibits.remove(currentExhibit);
                explorer.search();
            }
        }


        /**
         * Greshna implementaciq!!!!!
         */

//        for (Explorer explorer : explorers) {
//            Suitcase suitcase = explorer.getSuitcase();
//            Collection<String> exhibits = suitcase.getExhibits();
//            while (explorer.canSearch() && state.getExhibits().size() > 0) {
//                for (String exhibit : state.getExhibits()) {
//                    explorer.search();
//                    exhibits.add(exhibit);
//                }
//
//                for (String exhibit : exhibits) {
//                    state.getExhibits().remove(exhibit);
//                }
//
//            }
//        }


    }

}
