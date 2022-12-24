import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class T10ValidateCraftPresentMethod {
    private static final String METHOD_INCORRECT_RETURN_VALUE = "Method '%s.%s' returns invalid data (expected res: '%s'; actual - '%s')!";
    private static final String METHOD_INCORRECT_EXCEPTION_MESSAGE = "Method '%s.%s' throws invalid ex (expected: '%s - %s'; actual - '%s - %s')!";


    private Class classObject;
    private Object classType;

    @Before
    public void beforeMethod() {

        Class controllerClass = getType("ControllerImpl");

        Object controller = createObjectInstance(controllerClass, new Object[]{});

        this.classObject = controllerClass;
        this.classType = controller;
    }

    @Test
    public void validateCraftMethod() {
        getMethodValue(this.classType, this.classObject, "addPresent", new Object[]{"Doll", 30}, String.class, int.class);
        getMethodValue(this.classType, this.classObject, "addHelper", new Object[]{"Happy", "Pesho"}, String.class, String.class);
        getMethodValue(this.classType, this.classObject, "addHelper", new Object[]{"Happy", "Gosho"}, String.class, String.class);
        getMethodValue(this.classType, this.classObject, "addInstrumentToHelper", new Object[]{"Pesho", 10}, String.class, int.class);
        getMethodValue(this.classType, this.classObject, "addInstrumentToHelper", new Object[]{"Pesho", 50}, String.class, int.class);


        // Arrange
        String methodName = "craftPresent";
        Object[] methodArgs = new Object[]{"Doll"};
        Class[] paramTypes = new Class[]{String.class};

        String expectedMessage = "Present Doll is done. 1 instrument/s have been broken while working on it!";

        // Act
        Object actualResult = getMethodValue(this.classType, this.classObject, methodName, methodArgs, paramTypes);

        // Assert
        String message = String.format(METHOD_INCORRECT_RETURN_VALUE, this.classType.getClass().getSimpleName(), methodName, expectedMessage, actualResult);
        Assert.assertEquals(message, expectedMessage, actualResult);
    }

    private Object getMethodValue(Object object, Class clazz, String methodName, Object[] methodArgs, Class... parameterTypes) {
        Method method = getMethod(clazz, methodName, parameterTypes);

        Object methodValue = null;
        if (method != null) {
            try {
                methodValue = method.invoke(object, methodArgs);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }

        return methodValue;
    }

    private Object throwMethodException(String expectedExceptionName, String expectedExceptionMessage, Object object, Class clazz, String methodName, Object[] methodArgs, Class... parameterTypes) throws Throwable {
        Method method = getMethod(clazz, methodName, parameterTypes);

        Object methodValue = null;
        if (method != null) {
            try {
                methodValue = method.invoke(object, methodArgs);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
                String testMessage = String.format(METHOD_INCORRECT_EXCEPTION_MESSAGE,
                        clazz.getSimpleName(),
                        methodName,
                        expectedExceptionName,
                        expectedExceptionMessage,
                        e.getTargetException().getClass().getSimpleName(),
                        e.getTargetException().getMessage());

                Assert.assertEquals(testMessage, expectedExceptionMessage, e.getTargetException().getMessage());
                throw e.getTargetException();
            }
        }

        return methodValue;
    }

    private Object createObjectInstance(Class clazz, Object[] arguments) {
        Class[] argumentTypes = null;

        if (arguments != null) {
            argumentTypes = Arrays.stream(arguments).map(Object::getClass).toArray(Class[]::new);
        }

        Constructor ctor = null;
        try {
            ctor = clazz.getDeclaredConstructor(argumentTypes);
        } catch (NoSuchMethodException e) {
            mapIntegerToInt(argumentTypes);

            try {
                ctor = clazz.getDeclaredConstructor(argumentTypes);
            } catch (NoSuchMethodException ex) {
                try {
                    argumentTypes = Arrays.stream(arguments).map(a -> a.getClass().getInterfaces()[0]).toArray(Class[]::new);
                    ctor = clazz.getDeclaredConstructor(argumentTypes);
                } catch (NoSuchMethodException exc) {
                }
            }
        }

        Object obj = null;

        if (ctor != null) {
            try {
                obj = ctor.newInstance(arguments);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }

        return obj;
    }

    private void mapIntegerToInt(Class[] types) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].getSimpleName().equals(Integer.class.getSimpleName())) {
                types[i] = int.class;
            }
        }
    }

    private static Class getType(String name) {
        Class clazz = Classes.allClasses.get(name);

        return clazz;
    }

    private Method getMethod(Class clazz, String expectedName, Class... parameterTypes) {
        Method method = null;

        try {
            method = clazz.getMethod(expectedName, parameterTypes);
        } catch (NoSuchMethodException e) {
        }

        return method;
    }
}
