package com.wpoms.admin.services;

import com.wpoms.admin.models.payloads.RegisterCustomerPayload;
import com.wpoms.admin.models.payloads.UpdateCustomerPayload;
import com.wpoms.admin.models.response.RegisterCustomerResponse;

public interface ICustomerService {
    
    RegisterCustomerResponse registerCustomer(RegisterCustomerPayload payload);

    public RegisterCustomerResponse getCustomerById(Integer id);
    RegisterCustomerResponse updateCustomer(Integer id,UpdateCustomerPayload payload);

}
