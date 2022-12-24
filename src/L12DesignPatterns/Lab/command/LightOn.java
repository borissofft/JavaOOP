package L12DesignPatterns.Lab.command;

public class LightOn implements Command { // Команда

    private Lamp lamp;

    public LightOn(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public void execute() {
        this.lamp.on();
    }

}
