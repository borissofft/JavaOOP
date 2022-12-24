package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BlackBoxInt blackBoxInt = null;
        Field innerValue = null;

        try {

            Constructor constructor = BlackBoxInt.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            blackBoxInt = (BlackBoxInt) constructor.newInstance();

            innerValue = BlackBoxInt.class.getDeclaredField("innerValue");
            innerValue.setAccessible(true);

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        Method[] methods = BlackBoxInt.class.getDeclaredMethods();

        String input = scanner.nextLine();
        while (!"END".equals(input)) {

            String[] tokens = input.split("_");
            String command = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            Method method = Arrays.stream(methods)
                    .filter(m -> m.getName().equals(command))
                    .findFirst()
                    .orElse(null);

            method.setAccessible(true);

            try {

//                Class[] types = method.getParameterTypes();
//                Object[] params = new Object[]{value};
//
//                for (Class type : types) {
//                    for (Object param : params) {
//                        if (param.getClass().getSimpleName().toLowerCase().contains(type.getSimpleName())) {
//                            method.invoke(blackBoxInt, param);
//                        }
//                    }
//                }

                method.invoke(blackBoxInt, value);

            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(innerValue.getInt(blackBoxInt));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            input = scanner.nextLine();
        }


    }
}
