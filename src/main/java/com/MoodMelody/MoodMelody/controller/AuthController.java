package com.MoodMelody.MoodMelody.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.MoodMelody.MoodMelody.config.JWTUtil;
import com.MoodMelody.MoodMelody.dto.AuthRequest;
import com.MoodMelody.MoodMelody.dto.AuthResponse;
import com.MoodMelody.MoodMelody.entity.Role;
import com.MoodMelody.MoodMelody.entity.User;
import com.MoodMelody.MoodMelody.repository.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    // LOGIN API
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()
                )
        );

        String token = jwtUtil.generateToken(authRequest.email());

        return new AuthResponse(token);
    }
}
