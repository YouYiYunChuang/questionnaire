package com.yyc.access;

import java.lang.annotation.*;

/**
 * @author yuchengyao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    String[] value() default {};

    Role[] roles() default {};

}
