package com.robinvandenhurk.gateway.library.userinjection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinvandenhurk.gateway.library.userinjection.principal.AnonymousGatewayUserPrincipal;
import com.robinvandenhurk.gateway.library.userinjection.principal.AuthenticatedGatewayUserPrincipal;
import com.robinvandenhurk.gateway.library.userinjection.principal.GatewayUserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: ForwardedHttpServletRequest
 */

public class ForwardedHttpServletRequest extends HttpServletRequestWrapper {

    private GatewayUserPrincipal gatewayUserPrincipal;

    public ForwardedHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);

        ObjectMapper mapper = new ObjectMapper();
        String header = request.getHeader("gateway-user");

        if (header != null && !header.isEmpty()) {
            Map<String, Object> gatewayUserMap = mapper.readValue(Base64.getDecoder().decode(header.getBytes(StandardCharsets.UTF_8)), Map.class);

            if (gatewayUserMap.containsKey("id") && !gatewayUserMap.get("id").equals("-1")) {
//            Authenticated user
                this.gatewayUserPrincipal = AuthenticatedGatewayUserPrincipal.fromHeader(header);
            }
        }

        if (this.gatewayUserPrincipal == null) {
            this.gatewayUserPrincipal = new AnonymousGatewayUserPrincipal();
        }

    }

    @Override
    public GatewayUserPrincipal getUserPrincipal() {
        return gatewayUserPrincipal;
    }
}
