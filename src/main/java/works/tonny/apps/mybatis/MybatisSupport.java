package works.tonny.apps.mybatis;

import java.lang.annotation.*;

/**
 * Created by Tonny on 2016/4/16.
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MybatisSupport {
    String statement() default "";
}
