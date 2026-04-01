package com.wpoms.admin.services;

import com.wpoms.admin.models.payloads.EditVendorPayload;
import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.EditVendorResponse;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import org.springframework.http.ResponseEntity;

public interface IVendorService {
    RegisterVendorResponse registerVendor(RegisterVendorPayload payload);

    RegisterVendorResponse getVendor(Integer id);

    EditVendorResponse editVendor(Integer id, EditVendorPayload payload);


}
