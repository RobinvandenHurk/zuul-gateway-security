package com.robinvandenhurk.gateway.library.userinjection.annotation;

import com.robinvandenhurk.gateway.library.userinjection.domain.http.response.HttpResponse;
import com.robinvandenhurk.gateway.library.userinjection.principal.AuthenticatedGatewayUserPrincipal;
import com.robinvandenhurk.gateway.library.userinjection.principal.GatewayUserPrincipal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Author:    Robin van den Hurk
 * Date:      19/03/2021
 * File name: AuthorityRequiredAspect
 */

@Aspect
@Component
public class AuthorityRequiredAspect {

    private HttpServletRequest request;

    @Autowired
    public AuthorityRequiredAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("@annotation(com.robinvandenhurk.gateway.library.userinjection.annotation.AuthorityRequired)")
    public Object validateAspect(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        AuthorityRequired validateAction = method.getAnnotation(AuthorityRequired.class);
        String authority = validateAction.authority();
//        We can cast because all request go through the LoadUserFromHeaderFilter
        GatewayUserPrincipal userPrincipal = (GatewayUserPrincipal) this.request.getUserPrincipal();

        if (userPrincipal instanceof AuthenticatedGatewayUserPrincipal) {
            AuthenticatedGatewayUserPrincipal authenticatedUser = (AuthenticatedGatewayUserPrincipal) userPrincipal;

            if (authenticatedUser.getAuthorities().contains(authority)) {
                return pjp.proceed();
            } else {
                return HttpResponse.createForbidden();
            }
        } else {
            return HttpResponse.createUnauthorized();
        }
    }

}
