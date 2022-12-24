import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class T04ValidatePresentImplMethods {
    private static final String METHOD_NOT_PRESENT_ERROR_MESSAGE = "The method '%s.%s' does not exist(actual methods parameter types: '%s' ;expected - '%s')!";
    private static final String METHOD_HAS_WRONG_RETURN_TYPE = "The method '%s.%s()' has the wrong return type(actual - '%s'; expected - '%s')!";
    private static final String METHOD_HAS_PARAMETER_TYPES = "The method '%s.%s()' has incorrect parameter types(actual - '%s'; expected - '%s')!";

    private class ExpMethod {
        Class returnType;
        String name;
        Class[] parameterTypes;

        public ExpMethod(Class returnType, String name, Class... parameterTypes) {
            this.returnType = returnType;
            this.name = name;
            this.parameterTypes = parameterTypes;
        }
    }

    @Test
    public void validateClassMethods() {
        Class clazz = getType("PresentImpl");

        ExpMethod[] methods = new ExpMethod[]{
                new ExpMethod(String.class, "getName"),
                new ExpMethod(int.class, "getEnergyRequired"),
                new ExpMethod(void.class, "getCrafted"),
                new ExpMethod(boolean.class, "isDone"),
        };

        for (ExpMethod method : methods) {
            validateMethod(clazz, method);
        }
    }

    private void validateMethod(Class clazz, ExpMethod expMethod) {
        String expectedReturnType = expMethod.returnType.toString();
        String expectedName = expMethod.name;
        Class[] expectedParameterTypes = expMethod.parameterTypes;

        Method actualMethod = getMethod(clazz, expectedName, expectedParameterTypes);

        // Tests whether the method exist
        String actualMethodsParametersMessage = null;

        if (actualMethod == null) {
            actualMethodsParametersMessage = findMethodFromMethods(clazz, expectedName);
        }

        String existMessage = String.format(METHOD_NOT_PRESENT_ERROR_MESSAGE, clazz.getSimpleName(), expectedName, actualMethodsParametersMessage, arrayToString(expectedParameterTypes));
        Assert.assertNotNull(existMessage, actualMethod);

        // Tests whether the method returns correct type
        String actualReturnType = actualMethod.getReturnType().toString();
        String returnTypeMessage = String.format(METHOD_HAS_WRONG_RETURN_TYPE, clazz.getSimpleName(), expectedName, actualReturnType, expectedReturnType);
        Assert.assertEquals(returnTypeMessage, expectedReturnType, actualReturnType);
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
