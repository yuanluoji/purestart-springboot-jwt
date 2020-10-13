package com.github.yuanluoji.mbye.jwt;

import com.github.yuanluoji.mbye.utils.SpringContextUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuanluoji
 */
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    UserDetailsService userDetailsService;
    JwtTools jwt;

    private UserDetailsService getUserDetailsService() {
        if (null == userDetailsService) {
            userDetailsService = SpringContextUtil.getBean(UserDetailsService.class);
        }
        return userDetailsService;
    }

    private JwtTools getJwt() {
        if (null == jwt) {
            jwt = SpringContextUtil.getBean(JwtTools.class);
        }
        return jwt;
    }

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String token = request.getHeader("Authorization");

        Claims claims = null;
        try {
            claims = getJwt().getClaims(token);
        } catch (Exception ex) {
            log.error("JWT Token error: {} , cause: {}", token, ex.getMessage());
        }

        if (claims == null) {
            chain.doFilter(request, response);
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            boolean ok = getJwt().validateTokenExpiration(claims);
            if (ok) {
                UserDetails userDetails = getUserDetailsService().loadUserByUsername(claims.getSubject());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
