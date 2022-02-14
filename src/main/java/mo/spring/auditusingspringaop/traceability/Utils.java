package mo.spring.auditusingspringaop.traceability;

import java.lang.reflect.Field;

public class Utils {
    public static Long getObjectLongFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (Long) field.get(object);
    }
}
