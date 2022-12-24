import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class T08ValidateInterfaceCountMethods {
    private static final String METHOD_NOT_PRESENT_ERROR_MESSAGE = "The method '%s.%s' does not exist(actual param types: '%s' ;expected - '%s')!";
    private static final String INTERFACE_INVALID_METHODS_COUNT = "The interface '%s' has wrong methods count(expected - '%s'; actual - '%s')!";
    private static final String METHOD_HAS_PARAMETER_TYPES = "The method '%s.%s()' has incorrect parameter types(actual - '%s'; expected - '%s')!";

    private class Interface {
        String type;
        int methodsCount;

        public Interface(String type, int methodsCount) {
            this.type = type;
            this.methodsCount = methodsCount;
        }
    }

    @Test
    public void validateClassMethods() {
        Interface[] interfaces = new Interface[]{
                new Interface("Helper", 6),
                new Interface("Instrument", 3),
                new Interface("Present", 4),
                new Interface("Repository", 4),
        };

        for (Interface anInterface : interfaces) {
            validateMethodsCount(anInterface);
        }
    }

    private void validateMethodsCount(Interface anInterface) {
        Class clazz = getType(anInterface.type);

        int actualMethodsCount = clazz.getMethods().length;
        int expectedMethodsCount = anInterface.methodsCount;
        String returnTypeMessage = String.format(INTERFACE_INVALID_METHODS_COUNT, clazz.getSimpleName(), expectedMethodsCount, actualMethodsCount);
        Assert.assertEquals(returnTypeMessage, expectedMethodsCount, actualMethodsCount);
    }

    private String arrayToString(Class[] array) {
        String[] stringArray = Arrays.stream(array).map(Class::getSimpleName).toArray(String[]::new);
        String arrayStr = String.join(", ", stringArray);

        return arrayStr;
    }

    private String findMethodFromMethods(Class clazz, String methodName) {
        Method[] methods = clazz.getMethods();

        Method[] methodsWithGivenName = Arrays.stream(methods).filter(m -> m.getName().equals(methodName)).toArray(Method[]::new);

        StringBuilder sb = new StringBuilder();

        for (Method method : methodsWithGivenName) {
            String parameterTypes = arrayToString(method.getParameterTypes());
            sb.append("{ " + parameterTypes + " } ");
        }

        return sb.toString().trim();
    }

    private Method getMethod(Class clazz, String expectedName, Class... parameterTypes) {
        Method method = null;

        try {
            method = clazz.getMethod(expectedName, parameterTypes);
        } catch (NoSuchMethodException e) {
        }

        return method;
    }

    private static Class getType(String name) {
        Class clazz = Classes.allClasses.get(name);

        return clazz;
    }
}
