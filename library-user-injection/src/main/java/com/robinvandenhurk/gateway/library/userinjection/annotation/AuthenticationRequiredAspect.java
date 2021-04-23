package com.robinvandenhurk.gateway.library.userinjection.annotation;

import com.robinvandenhurk.gateway.library.userinjection.domain.http.response.HttpResponse;
import com.robinvandenhurk.gateway.library.userinjection.principal.AuthenticatedGatewayUserPrincipal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:    Robin van den Hurk
 * Date:      19/03/2021
 * File name: AuthenticationRequiredAspect
 */

@Aspect
@Component
public class AuthenticationRequiredAspect {

    private HttpServletRequest request;

    @Autowired
    public AuthenticationRequiredAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("@annotation(AuthenticationRequired)")
    public Object validateAspect(ProceedingJoinPoint pjp) throws Throwable {
        if (this.request.getUserPrincipal() instanceof AuthenticatedGatewayUserPrincipal) {
            return pjp.proceed();
        } else {
            return HttpResponse.createUnauthorized();
        }
    }

}
