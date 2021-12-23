package vsu.shirnin.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Implements dependency injection into any object
 * that contains fields marked with the @AutoInjectable annotation
 * and initializes them using reflection
 * @param <T> type of any class
 */
public class Injector<T> {
    /**
     * contains an object with key-value properties: interface-class
     */
    private final Properties properties;

    /**
     * property-reading class constructor
     */
    Injector() {
        FileInputStream fis;
        this.properties = new Properties();
        try {
            fis = new FileInputStream("src/main/java/vsu/shirnin/reflection/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error: properties not found :(");
        }
    }

    /**
     * Any object examines it for fields
     * with the @AutoInjectable annotation
     * and initialize it by the properties file
     *
     * @param object any object
     * @return object with initialized fields
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public T Inject(T object) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Field[] filedList = object.getClass().getDeclaredFields();

        for (Field field : filedList) {
            if (field.getAnnotation(AutoInjectable.class) != null) {
                String fieldType = field.getType().toString().split(" ")[1];

                String newClassName = properties.getProperty(fieldType);

                // remove the last extra character ';' with the full type name
                newClassName = newClassName.substring(0, newClassName.length() - 1);

                Class<?> whatClass = Class.forName(newClassName);

                field.setAccessible(true);
                field.set(object, whatClass.newInstance());
            }
        }
        return object;
    }

}
