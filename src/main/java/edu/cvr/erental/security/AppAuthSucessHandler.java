package edu.cvr.erental.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AppAuthSucessHandler implements  AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
        //request.getRequestDispatcher("/home").forward(request, response);
        redirectStrategy.sendRedirect(request, response, "/home");
    }
    
    
}
