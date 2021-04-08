package com.robinvandenhurk.gateway.library.userinjection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:    Robin van den Hurk
 * Date:      19/03/2021
 * File name: AuthenticationRequired
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationRequired {

}
