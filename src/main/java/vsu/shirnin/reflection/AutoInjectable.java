package vsu.shirnin.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
// the annotation is stored in the .class file and is available at runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable{
}