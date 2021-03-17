package com.robinvandenhurk.gateway.library.userinjection;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: LoadUserFromHeaderFilter
 */

public class LoadUserFromHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        Load the data from the original request into our custom request
//        This also adds the GatewayUser Principal, which makes it available
//        In controller classes
        ForwardedHttpServletRequest forwardedHttpServletRequest = new ForwardedHttpServletRequest(httpServletRequest);

        filterChain.doFilter(forwardedHttpServletRequest, httpServletResponse);
    }
}
