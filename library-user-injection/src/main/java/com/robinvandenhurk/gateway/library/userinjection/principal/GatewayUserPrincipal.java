package com.robinvandenhurk.gateway.library.userinjection.principal;

import java.security.Principal;

/**
 * Author:    Robin van den Hurk
 * Date:      16/03/2021
 * File name: GatewayUserPrincipal
 */

public abstract class GatewayUserPrincipal implements Principal {

    private String name;
    private Long id;

    public GatewayUserPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

}
