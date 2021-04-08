package com.robinvandenhurk.gateway.library.userinjection.domain.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinvandenhurk.gateway.library.userinjection.domain.http.response.data.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: HttpResponse
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse<T extends HttpResponseData> extends ResponseEntity<T> {
    private String error;

    public HttpResponse(String error, HttpStatus statusCode) {
        this(statusCode);
        this.error = error;
    }

    public HttpResponse() {
        this(HttpStatus.OK);
    }

    public HttpResponse(HttpStatus statusCode) {
        super(statusCode);
    }

    public HttpResponse(T data) {
        this(data, HttpStatus.OK);
    }

    public HttpResponse(T data, HttpStatus status) {
        super(data, status);
    }

    public String getError() {
        return error;
    }

    public static HttpResponse<?> createNotFound() {
        return createNotFound("Requested resource not found");
    }

    public static HttpResponse<?> createNotFound(String message) {
        return new HttpResponse<>(new NotFoundResponseData(message), HttpStatus.NOT_FOUND);
    }

    public static HttpResponse<?> createOK() {
        return new HttpResponse<>(HttpStatus.OK);
    }

    public static HttpResponse<?> createBadRequest(String message, Map<String, String> data) {
        return new HttpResponse<>(new BadRequestResponseData(message, data), HttpStatus.BAD_REQUEST);
    }

    public static HttpResponse<?> createBadRequest(String message) {
        return new HttpResponse<>(new BadRequestResponseData(message), HttpStatus.BAD_REQUEST);
    }

    public static HttpResponse<?> createConflict(String message) {
        return new HttpResponse<>(new ConflictResponseData(message), HttpStatus.CONFLICT);
    }

    public static HttpResponse<?> createForbidden() {
        return createForbidden(null);
    }

    public static HttpResponse<?> createForbidden(String authorityRequired) {
        return new HttpResponse<>(new ForbiddenResponseData("You are not authorized to access this resource", authorityRequired), HttpStatus.FORBIDDEN);
    }

    public static HttpResponse<?> createUnauthorized() {
        return new HttpResponse<>(new UnauthorizedResponseData("You do not have access to this resource"), HttpStatus.UNAUTHORIZED);
    }
}
