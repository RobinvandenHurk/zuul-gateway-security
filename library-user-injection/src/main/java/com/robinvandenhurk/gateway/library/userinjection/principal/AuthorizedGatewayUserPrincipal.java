package com.robinvandenhurk.gateway.library.userinjection.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: AuthorizedGatewayUserPrincipal
 */

public class AuthorizedGatewayUserPrincipal extends GatewayUserPrincipal {

    private String email;
    private List<String> authorities;

    public AuthorizedGatewayUserPrincipal() {
        super("AUTHENTICATED_USER");
    }

    public static AuthorizedGatewayUserPrincipal fromHeader(String header) throws JsonProcessingException {
        if (header != null && header.length() > 0) {
            String json = new String(Base64.getDecoder().decode(header));
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(json, AuthorizedGatewayUserPrincipal.class);
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
