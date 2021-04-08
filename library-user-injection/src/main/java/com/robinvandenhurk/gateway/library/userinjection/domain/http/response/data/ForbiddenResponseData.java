package com.robinvandenhurk.gateway.library.userinjection.domain.http.response.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinvandenhurk.gateway.library.userinjection.domain.http.response.HttpResponseData;

import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: ForbiddenResponseData
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForbiddenResponseData extends HttpResponseData {

    private String message;
    private String authorityRequired;

    public ForbiddenResponseData(String message) {
        this.message = message;
    }

    public ForbiddenResponseData(String message, String authorityRequired) {
        this.message = message;
        this.authorityRequired = authorityRequired;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthorityRequired() {
        return authorityRequired;
    }
}
