package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission {


    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        for (Astronaut astronaut : astronauts) {
            Collection<String> items = planet.getItems();
            while (astronaut.canBreath() && items.iterator().hasNext()) {
                astronaut.breath();
                String item = items.iterator().next();
                astronaut.getBag().getItems().add(item);
                items.remove(item);
            }
        }

        /**
         * Greshna logika!!!!
         */


//        for (Astronaut astronaut : astronauts) {
//            Collection<String> items = planet.getItems();
//            while (astronaut.canBreath() && !items.isEmpty()) {
//                for (String item : items) {
//                    astronaut.breath();
//                    astronaut.getBag().getItems().add(item);
//                }
//            }
//
//            for (String item : astronaut.getBag().getItems()) {
//                items.remove(item);
//            }
//
//        }



    }

}
