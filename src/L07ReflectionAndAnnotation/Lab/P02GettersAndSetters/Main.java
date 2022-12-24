package L07ReflectionAndAnnotation.Lab.P02GettersAndSetters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        Class reflection = Reflection.class;
        Method[] methods = reflection.getDeclaredMethods();

//        Method[] getters = Arrays.stream(methods)
//                .filter(m -> m.getName().startsWith("get") && m.getParameterCount() == 0) // Добра идея е да се филтрират гетърите и по това че нямат параметри!!!
//                .sorted(Comparator.comparing(Method::getName))
//                .toArray(Method[]::new);


        Method[] getters = getMethodsStartWith("get", methods);
        Method[] setters = getMethodsStartWith("set", methods);

        Arrays.stream(getters).forEach(getter -> System.out.printf("%s will return class %s%n", getter.getName(), getter.getReturnType().getName()));
        Arrays.stream(setters).forEach(setter -> System.out.printf("%s and will set field of class %s%n", setter.getName(), setter.getParameterTypes()[0].getName()));
    }

    private static Method[] getMethodsStartWith(String startWith, Method[] methods) {
        return Arrays.stream(methods)
                .filter(method -> method.getName().startsWith(startWith))
//                .sorted((f,s) -> f.getName().compareTo(s.getName()))
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);
    }

}
