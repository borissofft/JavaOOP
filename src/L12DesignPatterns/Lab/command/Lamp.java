package L12DesignPatterns.Lab.command;

public class Lamp { // Receiver - Обект който получава, че някаква команда е изпълнена

    public void on() {
        System.out.println("Lamp is on");
    }

    public void off() {
        System.out.println("Lamp is off");
    }

}
