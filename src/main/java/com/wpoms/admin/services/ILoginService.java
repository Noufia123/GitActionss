 package com.wpoms.admin.services;

import com.wpoms.admin.models.payloads.LoginPayload;
import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.LoginResponse;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import org.springframework.http.ResponseEntity;


public interface ILoginService {

    LoginResponse login(LoginPayload payload);
}
