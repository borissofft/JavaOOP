package L01WorkingWithAbstraction.Exercise.P04TrafficLights;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s");

        int n = Integer.parseInt(scanner.nextLine());

        TrafficLight[] lights = new TrafficLight[input.length];

        // попълваме масива с enums, в последователността по която са ни подадени от конзолата
        for (int i = 0; i < input.length; i++) {
            lights[i] = TrafficLight.valueOf(input[i]);
        }

        StringBuilder sb = new StringBuilder();
        TrafficLight[] lightsValues = {TrafficLight.RED, TrafficLight.GREEN, TrafficLight.YELLOW};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < lights.length; j++) {

                if (lights[j].ordinal() == 2) {
                    lights[j] = TrafficLight.RED;
                } else {
                    lights[j] = lightsValues[lights[j].ordinal() + 1];
                }

                sb.append(lights[j].toString()).append(" ");

            }

            sb.append(System.lineSeparator());

        }

        System.out.println(sb.toString());

    }
}
