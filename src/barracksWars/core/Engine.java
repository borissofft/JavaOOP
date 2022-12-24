package barracksWars.core;

import barracksWars.core.commands.Command;
import barracksWars.interfaces.*;
import barracksWars.interfaces.Runnable;
import jdk.jshell.spi.ExecutionControl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private static final String COMMANDS_PACKAGE_PATH = "barracksWars.core.commands.";

    /**
     * Task 4
     */

//    private Repository repository;
//    private UnitFactory unitFactory;


//    public Engine(Repository repository, UnitFactory unitFactory) {
//        this.repository = repository;
//        this.unitFactory = unitFactory;
//    }

    /**
     * Task 5 dependency injection
     */

    private CommandInterpreter commandInterpreter;
    public Engine(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
//                String result = interpretCommand(data, commandName);
                String result = this.commandInterpreter.interpretCommand(data, commandName).execute();
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
//            } catch (IOException | ExecutionControl.NotImplementedException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: refactor for problem 4
//    private String interpretCommand(String[] data, String commandName) {
//            throws ExecutionControl.NotImplementedException {
//        String result = "Invalid command!";
//        switch (commandName) {
//            case "add":
//                result = this.addUnitCommand(data);
//                break;
//            case "report":
//                result = this.reportCommand(data);
//                break;
//            case "fight":
//                result = this.fightCommand(data);
//                break;
//            default:
//                throw new RuntimeException("Invalid command!");
//        }
//    }



//    private String interpretCommand(String[] data, String commandName) {
//
//    // TODO: refactor for problem 4
//
//        String result = "Invalid command!";
//        commandName = Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1);
//
//        try {
//            Class<?> clazz = Class.forName(COMMANDS_PACKAGE_PATH + commandName);
//            Constructor<?> constructor = clazz.getDeclaredConstructor(String[].class, Repository.class, UnitFactory.class);
//            constructor.setAccessible(true); // Правим protected конструктора на класовете команди за да има енкапсулация, и за да можем да имаме достъп до него,
//                                            // го сетваме да е достъпен, иначе нама да е
//            Command command = (Command) constructor.newInstance(data, this.repository, this.unitFactory);
//            result = command.execute();
//        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }



//    private String reportCommand(String[] data) { // Създаден нов клас за задача 4 -> Add
//        String output = this.repository.getStatistics();
//        return output;
//    }

//    private String addUnitCommand(String[] data) throws ExecutionControl.NotImplementedException { // Създаден нов клас за задача 4 -> Add
//        String unitType = data[1];
//        Unit unitToAdd = this.unitFactory.createUnit(unitType);
//        this.repository.addUnit(unitToAdd);
//        String output = unitType + " added!";
//        return output;
//    }

//    private String fightCommand(String[] data) { // Създаден нов клас за задача 4 -> Add
//        return "fight";
//    }

}
