package io.github.youthred.goc.testapi.config;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface X {

    String value() default "X_O";
}
