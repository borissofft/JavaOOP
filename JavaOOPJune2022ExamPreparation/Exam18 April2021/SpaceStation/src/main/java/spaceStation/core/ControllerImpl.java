package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private int exploredPlanets = 0;

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut = null;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.add(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.asList(items));
        this.planetRepository.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = this.astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        this.astronautRepository.remove(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {

        Collection<Astronaut> astronauts = this.astronautRepository.getModels()
                .stream().filter(astronaut -> astronaut.getOxygen() > 60)
                .collect(Collectors.toCollection(ArrayList::new));
        if (astronauts.size() == 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Planet planet = this.planetRepository.findByName(planetName);
        Mission mission = new MissionImpl();
        mission.explore(planet, astronauts);
        exploredPlanets++;
        int countDead = (int) astronauts.stream().filter(astronaut -> astronaut.getOxygen() == 0).count();
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, countDead);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d planets were explored!", exploredPlanets)).append(System.lineSeparator());
        sb.append("Astronauts info:").append(System.lineSeparator());
        for (Astronaut astronaut : this.astronautRepository.getModels()) {
            sb.append(String.format("Name: %s", astronaut.getName())).append(System.lineSeparator());
            sb.append(String.format("Oxygen: %.0f", astronaut.getOxygen())).append(System.lineSeparator());
            sb.append("Bag items: ");
            if (astronaut.getBag().getItems().size() == 0) {
                sb.append("none").append(System.lineSeparator());
            } else {
                sb.append(String.join(", ", astronaut.getBag().getItems())).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

}
