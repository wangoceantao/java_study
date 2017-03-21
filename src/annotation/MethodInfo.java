package annotation;

import java.lang.annotation.*;

/**
 * Created by wanghaitao on 2016/9/27.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MethodInfo {
    String author() default "wangocenatao@163.com";

    int version() default 1;
}
