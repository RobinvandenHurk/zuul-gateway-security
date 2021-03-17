package com.robinvandenhurk.gateway.library.userinjection.principal;

/**
 * Author:    Robin van den Hurk
 * Date:      16/03/2021
 * File name: AnonymousGatewayUserPrincipal
 */

public class AnonymousGatewayUserPrincipal extends GatewayUserPrincipal {

    public AnonymousGatewayUserPrincipal() {
        super("ANONYMOUS_USER");

        setId(-1);
    }

}
