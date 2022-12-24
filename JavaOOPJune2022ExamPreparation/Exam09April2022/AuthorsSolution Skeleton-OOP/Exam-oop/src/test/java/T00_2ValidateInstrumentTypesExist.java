import org.junit.Assert;
import org.junit.Test;

public class T00_2ValidateInstrumentTypesExist {
    private static final String CLASS_NOT_PRESENT_ERROR_MESSAGE = "Class '%s' not present";

    @Test
    public void validateTypesExist() {
        String[] classTypesToAssert = new String[]{
                "Instrument",
                "InstrumentImpl",

        };

        for (String classType : classTypesToAssert) {
            String message = String.format(CLASS_NOT_PRESENT_ERROR_MESSAGE, classType);
            Assert.assertNotNull(message, getType(classType));
        }
    }

    private static Class getType(String name) {
        Class clazz = Classes.allClasses.get(name);

        return clazz;
    }
}