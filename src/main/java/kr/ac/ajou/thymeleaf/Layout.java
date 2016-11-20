package kr.ac.ajou.thymeleaf;

/**
 * Created by huy on 2016. 11. 20..
 */
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {
  String value() default "";
}