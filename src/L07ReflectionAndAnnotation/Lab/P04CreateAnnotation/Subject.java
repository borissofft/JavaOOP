package L07ReflectionAndAnnotation.Lab.P04CreateAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})// къде се използва, върху какви елементи може да се поставя - полета, методи, класове...Може със запетая да се изброят повече елементи от един.
@Retention(RetentionPolicy.RUNTIME) // кога се използва
public @interface Subject {

    String[] categories();

}
