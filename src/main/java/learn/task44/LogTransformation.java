package learn.task44;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface LogTransformation {
    @Value("${spring.application.pathlog}")
    String value() default "";
}
