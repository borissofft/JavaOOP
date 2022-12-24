package L07ReflectionAndAnnotation.Lab.P01Reflection;

import java.lang.reflect.InvocationTargetException;

/**
 * L07ReflectionAndAnnotation.Lab.P01Reflection
 *
 * При Reflection API не е нужно да създаваме инстанция от даден обект, за да имаме достъп до него и всичките му полета, методи, конструктори ...
 * можем да променим например дефолтни стойности на полета, още преди да сме компилирали дадения Class, това директно чупи всякаква енкапсулация
 */

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {

//        Class reflection = Reflection.class;
//        System.out.println(reflection);
//        Class superClass = reflection.getClass().getSuperclass();
//        System.out.println(superClass);
//        Class[] interfaces = reflection.getInterfaces();

        /**
         * Създаваме променлива от тип Class, можем да я използваме за да извикваме всякави рефлекшъни върху този клас,
         * не е нужно да създаваме нови променливи, както в по-горният пример - Class reflection, Class superClass ...
         */

//        Class someClass = Reflection.class;
        Class<?> someClass = Class.forName("L07ReflectionAndAnnotation.Lab.P01Reflection.Reflection"); // Class.forName("Целия път до класа!!!"), връща името на класа, хвърля и Exception
//        Class<?> someClass = Class.forName("Reflection"); // Лъжем Judge за пълния път до класа!!!
        System.out.println(someClass);
        System.out.println(someClass.getSuperclass());
        Class[] interfaces = someClass.getInterfaces();


        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }

//        Constructor[] constructors = someClass.getDeclaredConstructors();

        Reflection newReflection = (Reflection) someClass.getConstructor().newInstance();
        System.out.println(newReflection);

//        for (Constructor constructor : constructors) {
//            constructor.newInstance();
//        }

    }
}
