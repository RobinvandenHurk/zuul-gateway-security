package com.robinvandenhurk.gateway.example.serviceauthorization.controller;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.User;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.http.request.TokenRequest;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.http.request.VerifyRequest;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.http.response.HttpResponse;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.http.response.TokenResponseData;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.http.response.VerifyResponseData;
import com.robinvandenhurk.gateway.example.serviceauthorization.provider.AuthenticationProvider;
import com.robinvandenhurk.gateway.example.serviceauthorization.provider.JwtProvider;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: AuthenticationController
 */

@RestController
@RequestMapping("/auth/token")
public class AuthenticationController {

    private UserRepository userRepository;
    private JwtProvider tokenProvider;

    @Autowired
    private AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.tokenProvider = new JwtProvider();
    }

    /*
     This method validates the provided credentials and, when successful, returns a JWT Token
     */
    @PostMapping
    public ResponseEntity<HttpResponse> getToken(@RequestBody TokenRequest data) {
        AuthenticationProvider authProvider = new AuthenticationProvider();
        ResponseEntity<HttpResponse> response;

        if (authProvider.authenticate(userRepository, data.getEmail(), data.getPassword())) {
            User user = userRepository.findByEmail(data.getEmail());
            String token = tokenProvider.generateToken(user);
            response = ResponseEntity.ok(new HttpResponse(new TokenResponseData(token)));
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HttpResponse("Invalid credentials"));
        }

        return response;
    }

    /*
     This method verifies the submitted token on validness
     */
    @PostMapping("/verify")
    public ResponseEntity<HttpResponse> verifyToken(@RequestBody VerifyRequest request) {
        String[] tokenSplit = request.getToken().split(" ");

//      Remove token type if present
        String token = tokenSplit[tokenSplit.length - 1];

        if (tokenProvider.validateToken(token)) {
            String subject = tokenProvider.extractClaim(token, "sub", String.class);
            String email = tokenProvider.extractClaim(token, "email", String.class);
            List<String> authorities = tokenProvider.extractClaim(token, "authorities", List.class);

            return ResponseEntity.ok(new HttpResponse(new VerifyResponseData(subject, email, authorities)));
        } else {
            return ResponseEntity.ok(new HttpResponse(new VerifyResponseData()));
        }
    }

}
