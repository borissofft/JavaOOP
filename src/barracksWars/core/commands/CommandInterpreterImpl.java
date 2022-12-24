package barracksWars.core.commands;

import barracksWars.interfaces.CommandInterpreter;
import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {

    private static final String COMMANDS_PACKAGE_PATH = "barracksWars.core.commands.";

    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {

        Executable executable = null;

        commandName = Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1);

        try {
            Class<?> clazz = Class.forName(COMMANDS_PACKAGE_PATH + commandName);
            Constructor<?> constructor = clazz.getDeclaredConstructor(String[].class);
            constructor.setAccessible(true); // Правим protected конструктора на класовете команди за да има енкапсулация, и за да можем да имаме достъп до него,
            // го сетваме да е достъпен, иначе нама да е
            executable = (Executable) constructor.newInstance((Object) data);

            this.injectFields(executable);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return executable;
    }

    private void injectFields(Executable executable) {

        Field[] fields = executable.getClass().getDeclaredFields();
        Field[] currentFields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getAnnotations()[0].toString().contains("Inject")) {
                for (Field currentField : currentFields) {
                    if (currentField.getType().equals(field.getType())) {
                        field.setAccessible(true);
                        try {
   // искаш да сетнеш // това поле на този обект    с това поле което е текущо в момента на commandInterpreter(текущата инстанция на commandInterpreter и взимаме полето му)
                            field.set(executable, currentField.get(this));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }


}
