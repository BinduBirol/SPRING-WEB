package com.birol.config;

import java.io.IOException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // Capture the error cause
        String errorMessage = "Authentication failed. Please try again.";

        // Check the specific cause of the failure
        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid username or password.";
        } else if (exception instanceof LockedException) {
            errorMessage = "Your account is locked.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "Your account is disabled.";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "Your account has expired.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "Your credentials have expired.";
        }
        
        request.getSession().setAttribute("loginError", errorMessage);
        // Redirect to the login page with flash attributes
        response.sendRedirect(request.getContextPath() + "/login?error=true");
    }
}
