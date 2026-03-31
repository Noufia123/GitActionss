package com.wpoms.admin.controllers;

import com.wpoms.admin.models.payloads.LoginPayload;
import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.LoginResponse;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import com.wpoms.admin.services.ILoginService;
import com.wpoms.admin.services.IVendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

    @Autowired
    ILoginService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginPayload payload){
        LoginResponse response=service.login(payload);
        return ResponseEntity.ok(response);
    }
}