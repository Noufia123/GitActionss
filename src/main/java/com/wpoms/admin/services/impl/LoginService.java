package com.wpoms.admin.services.impl;

import com.wpoms.admin.models.entities.UserMaster;
import com.wpoms.admin.models.payloads.LoginPayload;
import com.wpoms.admin.models.response.LoginResponse;
import com.wpoms.admin.repositories.UserMasterRepository;
import com.wpoms.admin.services.ILoginService;
import com.wpoms.admin.utilities.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LoginService implements ILoginService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserMasterRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginPayload payload) {

        UserMaster user = userRepository.findByEmail(payload.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!bCryptPasswordEncoder.matches(payload.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // GENERATE TOKEN WITH EMAIL AND ROLE
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        LoginResponse response = new LoginResponse();
        response.setMessage("Login successful");
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setToken(token);

        return response;
    }
}