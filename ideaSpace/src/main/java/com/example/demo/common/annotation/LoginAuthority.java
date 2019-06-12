package com.example.demo.common.annotation;

import java.lang.annotation.*;

/**
 * Created by morris on 9/9/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAuthority {

    LoginAuthType authType() default LoginAuthType.REQUEST_BODY_AUTH;

}
