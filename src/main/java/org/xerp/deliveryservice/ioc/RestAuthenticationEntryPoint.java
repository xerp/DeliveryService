package org.xerp.deliveryservice.ioc;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        var writer = response.getWriter();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        writer.println("HTTP Status 401 - " + authException.getMessage());
    }
}
