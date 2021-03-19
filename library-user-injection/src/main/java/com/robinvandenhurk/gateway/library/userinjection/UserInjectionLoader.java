package com.robinvandenhurk.gateway.library.userinjection;

import com.robinvandenhurk.gateway.library.userinjection.annotation.AuthorityRequiredAspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Author:    Robin van den Hurk
 * Date:      19/03/2021
 * File name: UserInjectionLoader
 */

@ComponentScan
@Import({LoadUserFromHeaderFilter.class, AuthorityRequiredAspect.class})
public class UserInjectionLoader {
}
