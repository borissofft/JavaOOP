package L07ReflectionAndAnnotation.Lab.P03HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        Class<?> reflection = Reflection.class;
        Method[] methods = reflection.getDeclaredMethods();

        Method[] getters = getMethodsStartWith("get", methods);
        Method[] setters = getMethodsStartWith("set", methods);
        Field[] fields = reflection.getDeclaredFields();

        Arrays.stream(fields)
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(field -> System.out.printf("%s must be private!%n", field.getName()));

        Arrays.stream(getters)
                .filter(g -> !Modifier.isPublic(g.getModifiers()))
                .forEach(getter -> System.out.printf("%s have to be public!%n", getter.getName()));
        Arrays.stream(setters)
                .filter(g -> !Modifier.isPrivate(g.getModifiers()))
                .forEach(setter -> System.out.printf("%s have to be private!%n", setter.getName()));
    }

    private static Method[] getMethodsStartWith(String startWith, Method[] methods) {
        return Arrays.stream(methods)
                .filter(method -> method.getName().startsWith(startWith))
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);
    }

}

