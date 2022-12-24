package L04InterfacesAndAbstraction.Lab.P05BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Може да се направи решение, като общото поле id за Citizen и Robot се изнесе в абстрактен клас. Това решение обаче няма д амине в Judge, но не е грешно.
 * Грешно е само от гледна точка на диаграмата, която е дадена в условието на задачата!!!
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

//        List<Robot> robots = new ArrayList<>();
//        List<Citizen> citizens = new ArrayList<>();

        List<Identifiable> identifiables = new ArrayList<>();

        while (!"End".equals(input)) {
            String[] data = input.split("\\s+");

            if (data.length == 3) {
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String id = data[2];
//                citizens.add(new Citizen(name, age, id));
                identifiables.add(new Citizen(name, age, id));
            } else {
                String model = data[0];
                String id = data[1];
//                robots.add(new Robot(model, id));
                identifiables.add(new Robot(model, id));
            }

            input = scanner.nextLine();
        }

//        List<String> fakeIds = new ArrayList<>();
        String lastFakeDigits = scanner.nextLine();
//
//        for (Robot robot : robots) {
//            if (robot.getId().endsWith(lastFakeDigits)) {
//                fakeIds.add(robot.getId());
//            }
//        }
//
//
//        for (Citizen citizen : citizens) {
//            if (citizen.getId().endsWith(lastFakeDigits)) {
//                fakeIds.add(citizen.getId());
//            }
//        }
//
//        if (!fakeIds.isEmpty()) {
//            fakeIds.forEach(System.out::println);
//        }

        for (Identifiable identifiable : identifiables) {
            if (identifiable.getId().endsWith(lastFakeDigits)) {
                System.out.println(identifiable.getId());
            }
        }


    }
}
