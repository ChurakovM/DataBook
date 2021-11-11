package com.example.authenticationservice.security;

import com.example.authenticationservice.requests.LoginRequest;
import com.example.authenticationservice.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthService authService;
    private final Environment environment;
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        try {
            LoginRequest credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(credentials.getUserName(), credentials.getPassword()));
        } catch (IOException ex) {
            throw new RuntimeException("Something went wrong with the login functionality", ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
        Authentication authResult) {
        String userName = ((User) authResult.getPrincipal()).getUsername();
        UserDetails userDetails = authService.loadUserByUsername(userName);

        String token = Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
            .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
            .compact();

        response.addHeader("token", token);
        response.addHeader("userName", userDetails.getUsername());
    }
}
