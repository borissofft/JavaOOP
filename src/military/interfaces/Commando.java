package military.interfaces;

import models.Mission;

import java.util.Collection;

public interface Commando {

    void addMission(Mission missionState);

    Collection<Mission> getMissions();

}
