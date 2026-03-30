package com.wpoms.admin.services;

import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import org.springframework.http.ResponseEntity;

public interface IVendorService {
    ResponseEntity<RegisterVendorResponse> registerVendor(RegisterVendorPayload payload);

    ResponseEntity<RegisterVendorResponse> getVendor(Integer id);

    ResponseEntity<RegisterVendorResponse> editVendor(Integer id, RegisterVendorPayload payload);
}
