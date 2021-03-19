package com.robinvandenhurk.gateway.library.userinjection.response;

/**
 * Author:    Robin van den Hurk
 * Date:      19/03/2021
 * File name: AuthorityRequiredResponse
 */

public class AuthorityRequiredResponse extends AbstractResponse {

    private String authorityRequired;

    public AuthorityRequiredResponse(String authorityRequired) {
        super("You do not have the correct authorities to connect to this endpoint");
        this.authorityRequired = authorityRequired;
    }

    public String getAuthorityRequired() {
        return authorityRequired;
    }
}
