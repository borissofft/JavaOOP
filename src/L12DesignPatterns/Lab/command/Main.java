package L12DesignPatterns.Lab.command;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

//        Lamp lamp = new Lamp();

        Lamp lampInKitchen = new Lamp();
        Lamp lampInBedRoom = new Lamp();
        Lamp lampInBathroom = new Lamp();

        Switch invoker = new Switch();

//        invoker.storeAndExecute(new LightOn(lamp));
//        invoker.storeAndExecute(new LightOff(lamp));
//        invoker.undoExecuted();

        invoker.storeAndExecute(new TurnAllLightsOn(Arrays.asList(lampInKitchen, lampInBedRoom, lampInBathroom)));

    }
}
