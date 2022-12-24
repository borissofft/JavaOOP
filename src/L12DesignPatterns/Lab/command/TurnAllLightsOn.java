package L12DesignPatterns.Lab.command;

import java.util.List;

public class TurnAllLightsOn implements Command { // Команда

    private List<Lamp> lamps;

    public TurnAllLightsOn(List<Lamp> lamps) {
        this.lamps = lamps;
    }

    @Override
    public void execute() {
        for (Lamp lamp : lamps) {
            lamp.on();
        }
    }
}
