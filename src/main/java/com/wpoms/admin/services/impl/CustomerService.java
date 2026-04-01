package com.wpoms.admin.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wpoms.admin.models.entities.CustomerMaster;
import com.wpoms.admin.models.entities.UserMaster;
import com.wpoms.admin.models.payloads.RegisterCustomerPayload;
import com.wpoms.admin.models.payloads.UpdateCustomerPayload;
import com.wpoms.admin.models.response.RegisterCustomerResponse;
import com.wpoms.admin.repositories.CustomerRepository;
import com.wpoms.admin.repositories.UserMasterRepository;
import com.wpoms.admin.services.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    private UserMasterRepository _userMasterRepository;
    private CustomerRepository _customerRepository;

    public CustomerService(UserMasterRepository _userMasterRepository, CustomerRepository _customerRepository) {
        this._userMasterRepository = _userMasterRepository;
        this._customerRepository = _customerRepository;
    }


     // Register customer

    @Autowired
    BCryptPasswordEncoder bcrypt;
    @Override
    public RegisterCustomerResponse registerCustomer(RegisterCustomerPayload payload) {
        RegisterCustomerResponse response = new RegisterCustomerResponse();

        try {
            if (_customerRepository.existsByCustomerEmail(payload.getEmail())) {
                response.setMessage("Email already exists for customer");
                return response;
            }

            UserMaster user = new UserMaster();
            user.setEmail(payload.getEmail());

            user.setPasswordHash(bcrypt.encode(payload.getPasswordHash()));
            user.setRole(payload.getRole());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setActive(true);

            UserMaster savedUser = _userMasterRepository.save(user);

            CustomerMaster customer = new CustomerMaster();
            customer.setUserId(savedUser.getId());
            customer.setCustomerEmail(savedUser.getEmail());

            customer.setCustomerName(payload.getCustomerName());
            customer.setPhoneNo(payload.getPhoneNo());
            customer.setDob(payload.getDateOfBirth());
            customer.setShippingAddress(payload.getShippingAddress());
            customer.setContactPreference(payload.getContactPreference());

            CustomerMaster savedCustomer = _customerRepository.save(customer);
            response.setCustomerId(savedCustomer.getCustomerId());
            response.setUserId(savedUser.getId());
            response.setCustomerName(savedCustomer.getCustomerName());
            response.setCustomerEmail(savedUser.getEmail());

            response.setPhoneNo(savedCustomer.getPhoneNo());
            response.setDateOfBirth(savedCustomer.getDob());
            response.setShippingAddress(savedCustomer.getShippingAddress());
            response.setContactPreference(savedCustomer.getContactPreference());

            response.setMessage("Customer Registered Successfully");
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setMessage("Error Registering Customer -" + e.getMessage());
            return response;
        }
    }

    //View customer

    @Override

    public RegisterCustomerResponse getCustomerById(Integer id) {
        RegisterCustomerResponse viewResponse = new RegisterCustomerResponse();
        Optional<CustomerMaster> optionalCustomer = _customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            CustomerMaster customer = optionalCustomer.get();
            viewResponse.setUserId(customer.getUserId());
            viewResponse.setCustomerId(customer.getCustomerId());
            viewResponse.setCustomerEmail(customer.getCustomerEmail());
            viewResponse.setCustomerName(customer.getCustomerName());
            viewResponse.setPhoneNo(customer.getPhoneNo());
            viewResponse.setDateOfBirth(customer.getDob());
            viewResponse.setShippingAddress(customer.getShippingAddress());
            viewResponse.setContactPreference(customer.getContactPreference());

            viewResponse.setMessage("Customer fetched  successfully");
        } else {
            viewResponse.setMessage("Customer not found");
        }

        return viewResponse;

    }

    // update customer

    @Override
    public RegisterCustomerResponse updateCustomer(Integer id, UpdateCustomerPayload payload) {
        RegisterCustomerResponse response = new RegisterCustomerResponse();
        try {

            // Fetch customer
            Optional<CustomerMaster> optionalCustomer = _customerRepository.findById(id);

            CustomerMaster customer = optionalCustomer.orElse(null);

            if (customer == null) {
                response.setMessage("Customer not found");
                return response;

            }

            customer.setCustomerName(payload.getCustomerName());
            customer.setPhoneNo(payload.getPhoneNo());
            customer.setDob(payload.getDateOfBirth());
            customer.setShippingAddress(payload.getShippingAddress());
            customer.setContactPreference(payload.getContactPreference());

            CustomerMaster updateCustomer = _customerRepository.save(customer);
            response.setCustomerId(updateCustomer.getCustomerId());
            response.setUserId(updateCustomer.getUserId());
            response.setCustomerName(updateCustomer.getCustomerName());
            response.setDateOfBirth(updateCustomer.getDob());
            response.setCustomerEmail(updateCustomer.getCustomerEmail());
            response.setPhoneNo(updateCustomer.getPhoneNo());
            response.setContactPreference(updateCustomer.getContactPreference());
            response.setShippingAddress(updateCustomer.getShippingAddress());

            response.setMessage("Customer Updated Successfully");
            return response;
        } catch (Exception e) {
            response.setMessage("Failed to update customer profile" + e.getMessage());
            return response;
        }
    }
}