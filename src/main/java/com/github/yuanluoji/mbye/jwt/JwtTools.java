package com.github.yuanluoji.mbye.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author yuanluoji
 */
@Component
public class JwtTools {

    @Value("${mbye.jwt.secret}")
    private String secret = "bGsyMzRqbGs4MDIzNGxzZOWPr+i/nuaOpeWFi+mHjOaWr+acteWkq2lzb2Zpb3MyM3U4NDMybmRzZGZzb2tqampza2xmanNsayVeJl4mJSQjJCQlIzgzIDEyPTEyeTN1aXV5Jl4=";

    @Value("${mbye.jwt.expire}")
    private int expire = 5 * 60 * 60;


    public String generateToken(Map<String, Object> claims, String subject) {
        Date now = Calendar.getInstance().getTime();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + expire * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateTokenExpiration(Claims claims) {
        final Date expiration = claims.getExpiration();
        return expiration.after(Calendar.getInstance().getTime());
    }

    public Claims getClaims(String token){
        Claims claims = Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
