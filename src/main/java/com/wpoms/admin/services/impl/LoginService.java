package com.wpoms.admin.services.impl;
import com.wpoms.admin.models.entities.UserMaster;
import com.wpoms.admin.models.payloads.LoginPayload;
import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.LoginResponse;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import com.wpoms.admin.repositories.UserMasterRepository;
import com.wpoms.admin.services.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginService implements ILoginService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserMasterRepository userRepository;

    @Override
    public LoginResponse login(LoginPayload payload) {


            UserMaster user=userRepository.findByEmail(payload.getEmail()).orElseThrow(()->new NoSuchElementException("User not found"));

            if(!bCryptPasswordEncoder.matches(payload.getPassword(),user.getPasswordHash())){
                throw new IllegalArgumentException("Invalid credentials");
            }
            LoginResponse response=new LoginResponse();
            response.setMessage("Login successful");
            response.setUserId(user.getId());
            response.setRole(user.getRole());
            return response;

    }
}
