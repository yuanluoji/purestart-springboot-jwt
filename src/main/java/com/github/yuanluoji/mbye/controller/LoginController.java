package com.github.yuanluoji.mbye.controller;

import com.github.yuanluoji.mbye.jwt.JwtRequest;
import com.github.yuanluoji.mbye.jwt.JwtResponse;
import com.github.yuanluoji.mbye.jwt.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTools jwt;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest)
            throws Exception {

        Authentication authentication = authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());


        User user = User.class.cast(authentication.getPrincipal());
        final String token = jwt.generateToken(new HashMap<>(), user.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private Authentication authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
