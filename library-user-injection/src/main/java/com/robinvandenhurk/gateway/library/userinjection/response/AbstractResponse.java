package com.robinvandenhurk.gateway.library.userinjection.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Author:    Robin van den Hurk
 * Date:      19/03/2021
 * File name: AbstractResponse
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractResponse {

    private boolean success;
    private String errorMessage;

    public AbstractResponse() {
        success = true;
    }

    public AbstractResponse(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
