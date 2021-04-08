package com.robinvandenhurk.gateway.library.userinjection.domain.http.response.data;

import com.robinvandenhurk.gateway.library.userinjection.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: ForbiddenResponseData
 */

public class NotFoundResponseData extends HttpResponseData {

    private String message;

    public NotFoundResponseData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
