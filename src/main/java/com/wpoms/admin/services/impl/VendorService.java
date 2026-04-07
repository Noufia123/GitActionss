package com.wpoms.admin.services.impl;

import com.wpoms.admin.models.entities.UserMaster;
import com.wpoms.admin.models.entities.VendorMaster;
import com.wpoms.admin.models.payloads.EditVendorPayload;
import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.EditVendorResponse;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import com.wpoms.admin.repositories.UserMasterRepository;
import com.wpoms.admin.repositories.VendorMasterRepository;
import com.wpoms.admin.services.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class VendorService implements IVendorService {

    @Autowired
    UserMasterRepository userRepository;

    @Autowired
    VendorMasterRepository vendorRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public RegisterVendorResponse registerVendor(RegisterVendorPayload payload) {
        RegisterVendorResponse response = new RegisterVendorResponse();
            if(vendorRepository.existsByBusinessEmail(payload.getVendorEmail())){
                throw new IllegalStateException("Email already exists for vendor");
            }
            if(vendorRepository.existsByGstNumber(payload.getGstNumber().toUpperCase())){
                throw new IllegalStateException("Gst number already exists for vendor");
            }

            if(userRepository.existsByEmail(payload.getEmail())){

                throw new IllegalStateException("Email already exists for user");
            }

            if(vendorRepository.existsByPhone(payload.getPhone())){

                throw new IllegalStateException("Phone number already exists for vendor");
            }

            UserMaster user = new UserMaster();
            user.setEmail(payload.getEmail());
            user.setPasswordHash(bCryptPasswordEncoder.encode(payload.getPassword()));
            user.setRole(payload.getRole());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setActive(true);
            userRepository.save(user);

            VendorMaster vendor = new VendorMaster();
            vendor.setUserId(user.getId());
            vendor.setVendorName(payload.getVendorName());
            vendor.setBusinessEmail(payload.getVendorEmail());
            vendor.setAddress(payload.getAddress());
            vendor.setPhone(payload.getPhone());
            vendor.setGstNumber(payload.getGstNumber().toUpperCase());
            vendorRepository.save(vendor);

            response.setMessage("Vendor registration successful");

            return response;
    }

    @Override
    public RegisterVendorResponse getVendor(Integer id) {
        RegisterVendorResponse response = new RegisterVendorResponse();

            VendorMaster vendor = vendorRepository.findByUserId(id).orElseThrow(()->new NoSuchElementException("Vendor not found"));
            response.setVendorName(vendor.getVendorName());
            response.setVendorEmail(vendor.getBusinessEmail());
            response.setAddress(vendor.getAddress());
            response.setPhone(vendor.getPhone());
            response.setGstNumber(vendor.getGstNumber());
            return response;
    }

    @Override
    public EditVendorResponse editVendor(Integer id, EditVendorPayload payload) {
        EditVendorResponse response=new EditVendorResponse();
            VendorMaster vendor = vendorRepository.findByUserId(id).orElseThrow(()->new NoSuchElementException("Vendor not found"));

            if (vendorRepository.existsByBusinessEmailAndUserIdNot(payload.getVendorEmail(), id)) {
                throw new IllegalStateException("Email already exists for another vendor");
            }
            if(vendorRepository.existsByGstNumberAndUserIdNot(payload.getGstNumber().toUpperCase(),id)){
                throw new IllegalStateException("Gst number already exists for another vendor");
            }
            if(vendorRepository.existsByPhoneAndUserIdNot(payload.getPhone(),id)){
                throw new IllegalStateException("Phone number already exists for another vendor");
            }

            vendor.setVendorName(payload.getVendorName());
            vendor.setAddress(payload.getAddress());
            vendor.setPhone(payload.getPhone());
            vendor.setGstNumber(payload.getGstNumber().toUpperCase());
            vendor.setBusinessEmail(payload.getVendorEmail());
            vendorRepository.save(vendor);
            response.setMessage("Vendor profile edited successfully");
            return response;

    }
}
