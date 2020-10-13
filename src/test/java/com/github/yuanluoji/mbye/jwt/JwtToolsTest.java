package com.github.yuanluoji.mbye.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author yuanluoji
 * @date 2020/10/12
 */
class JwtToolsTest {
    @Test
    public void testGen() {

        String key = new String(Base64.getEncoder().encode("lk234jlk80234lsd可连接克里斯朵夫isofios23u8432ndsdfsokjjjsklfjslk%^&^&%$#$$%#83 12=12y3uiuy&^".getBytes()));
        String key2 = new String(Base64.getEncoder().encode("_lk234jlk80234lsd可连接克里斯朵夫isofios23u8432ndsdfsokjjjsklfjslk%^&^&%$#$$%#83 12=12y3uiuy&^".getBytes()));
        HashMap claims = new HashMap<>();
        claims.put("a", "aaa");
        claims.put("b", "bbb");
        claims.put("c", "ccc");
        Date now = Calendar.getInstance().getTime();
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject("yuanluoji")
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + (1000L * 1000)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        System.out.println(key);


        assertThrows(SignatureException.class, () -> {
            Claims claimsUnpack = Jwts
                    .parser()
                    .setSigningKey(key2)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(claimsUnpack);
        });

    }
}