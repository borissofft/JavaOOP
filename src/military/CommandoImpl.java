package military;

import military.enums.Corps;
import military.interfaces.Commando;
import models.Mission;
import models.Repair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {

    private List<Mission> missions = new ArrayList<>();

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps, Mission... missions) {
        super(id, firstName, lastName, salary, corps);

        if (missions != null) { // Трябва да се премести в сетъри
            this.missions.addAll(Arrays.stream(missions).filter(e -> e != null).collect(Collectors.toList()));
        } else {
            this.missions = new ArrayList<>();
        }

    }

    @Override
    public void addMission(Mission missionState) {
        this.missions.add(missionState);
    }

    @Override
    public Collection<Mission> getMissions() {
        return this.getMissions();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Missions:").append(System.lineSeparator());
        for (Mission mission : missions) {
            sb.append("  ").append(mission.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim(); // Дори колекцията да е празна, след trim, ще отпечата само "Missions:"
    }

}
