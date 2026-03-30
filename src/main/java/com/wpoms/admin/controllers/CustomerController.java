package com.wpoms.admin.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.wpoms.admin.models.payloads.RegisterCustomerPayload;
import com.wpoms.admin.models.response.RegisterCustomerResponse;
import com.wpoms.admin.services.ICustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService)
    {
        this.customerService=customerService;
    }

    @PostMapping("/register-customer")
    public RegisterCustomerResponse registerCustomer( @Valid @RequestBody RegisterCustomerPayload payload) {
        RegisterCustomerResponse response=customerService.registerCustomer(payload);
        return  response;
        
    }

    @GetMapping("/view-customer")
    public RegisterCustomerResponse getCustomerById(@RequestParam Integer id )
    {
        return  customerService .getCustomerById(id);
    }

    


}
