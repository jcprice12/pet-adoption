package com.john.price.PetAdoption.Filters;

import static com.john.price.PetAdoption.Constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.john.price.PetAdoption.Constants.SecurityConstants.BEARER;

import com.john.price.PetAdoption.Services.JWTService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

  @Autowired private UserDetailsService userDetailsService;

  @Autowired private JWTService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    String authorizationHeaderValue = req.getHeader(AUTHORIZATION_HEADER);

    if (authorizationHeaderValue == null || !authorizationHeaderValue.startsWith(BEARER)) {
      chain.doFilter(req, res);
      return;
    }

    String username = jwtService.getUsernameFromToken(authorizationHeaderValue.replace(BEARER, ""));
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    SecurityContextHolder.getContext()
        .setAuthentication(
            username != null
                ? new UsernamePasswordAuthenticationToken(
                    username, null, userDetails.getAuthorities())
                : null);

    chain.doFilter(req, res);
  }
}
