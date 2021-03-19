package com.robinvandenhurk.gateway.library.userinjection.response;

/**
 * Author:    Robin van den Hurk
 * Date:      19/03/2021
 * File name: UnauthorizedResponse
 */

public class UnauthorizedResponse extends AbstractResponse {

    public UnauthorizedResponse() {
        super("You are unauthorized to access this endpoint");
    }
}
