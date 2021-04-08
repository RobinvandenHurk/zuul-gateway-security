package com.robinvandenhurk.gateway.library.userinjection.domain.http.response.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinvandenhurk.gateway.library.userinjection.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: UnauthorizedResponseData
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnauthorizedResponseData extends HttpResponseData {

    private String message;

    public UnauthorizedResponseData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
