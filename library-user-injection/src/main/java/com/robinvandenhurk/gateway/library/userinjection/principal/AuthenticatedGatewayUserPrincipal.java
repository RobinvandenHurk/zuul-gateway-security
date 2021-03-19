package com.robinvandenhurk.gateway.library.userinjection.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: AuthenticatedGatewayUserPrincipal
 */

public class AuthenticatedGatewayUserPrincipal extends GatewayUserPrincipal {

    private String email;
    private List<String> authorities;

    public AuthenticatedGatewayUserPrincipal() {
        super("AUTHENTICATED_USER");
    }

    public static AuthenticatedGatewayUserPrincipal fromHeader(String header) throws JsonProcessingException {
        if (header != null && header.length() > 0) {
            String json = new String(Base64.getDecoder().decode(header));
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(json, AuthenticatedGatewayUserPrincipal.class);
        } else {
            return null;
        }
    }

    public String getEmail() {
        return email;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

}
