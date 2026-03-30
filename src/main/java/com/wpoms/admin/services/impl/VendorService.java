package com.wpoms.admin.services.impl;

import com.wpoms.admin.models.entities.UserMaster;
import com.wpoms.admin.models.entities.VendorMaster;
import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import com.wpoms.admin.repositories.UserMasterRepository;
import com.wpoms.admin.repositories.VendorMasterRepository;
import com.wpoms.admin.services.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VendorService implements IVendorService {

    @Autowired
    UserMasterRepository userRepository;

    @Autowired
    VendorMasterRepository vendorRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<RegisterVendorResponse> registerVendor(RegisterVendorPayload payload) {
        RegisterVendorResponse response = new RegisterVendorResponse();
        try {
            if(vendorRepository.existsByBusinessEmail(payload.getVendorEmail())){
                response.setMessage("Email already exists for vendor");
                return ResponseEntity.badRequest().body(response);
            }
            if(vendorRepository.existsByGstNumber(payload.getGstNumber())){
                response.setMessage("Gst number already exists for vendor");
                return ResponseEntity.badRequest().body(response);
            }

            if(userRepository.existsByEmail(payload.getEmail())){
                response.setMessage("Email already exists for user");
                return ResponseEntity.badRequest().body(response);
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
            vendor.setGstNumber(payload.getGstNumber());

            vendorRepository.save(vendor);

            response.setVendor_id(vendor.getVendorId());
            response.setUserId(user.getId());
            response.setMessage("Vendor registration successfull");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Error registering vendor :"+e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public ResponseEntity<RegisterVendorResponse> getVendor(Integer id) {
        RegisterVendorResponse response = new RegisterVendorResponse();
        try {

            Optional<VendorMaster> optionalVendor = vendorRepository.findById(id);
            if (!optionalVendor.isPresent()) {
                response.setMessage("Vendor not found");
                return ResponseEntity.status(404).body(response);
            }
            VendorMaster vendor = optionalVendor.get();
            response.setVendorName(vendor.getVendorName());
            response.setVendorEmail(vendor.getBusinessEmail());
            response.setAddress(vendor.getAddress());
            response.setPhone(vendor.getPhone());
            response.setGstNumber(vendor.getGstNumber());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setMessage("Error fetching vendor details "+e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public ResponseEntity<RegisterVendorResponse> editVendor(Integer id, RegisterVendorPayload payload) {
        RegisterVendorResponse response=new RegisterVendorResponse();
        try{
            Optional<VendorMaster> optionalVendor = vendorRepository.findById(id);
            if (!optionalVendor.isPresent()) {
                response.setMessage("Vendor not found");
                return ResponseEntity.badRequest().body(response);
            }
            if (vendorRepository.existsByBusinessEmailAndVendorIdNot(payload.getVendorEmail(), id)) {
                response.setMessage("Email already exists for another vendor");
                return ResponseEntity.badRequest().body(response);
            }
            if(vendorRepository.existsByGstNumberAndVendorIdNot(payload.getGstNumber(),id)){
                response.setMessage("Gst number already exists for another vendor");
                return ResponseEntity.badRequest().body(response);
            }

            VendorMaster vendor=optionalVendor.get();
            vendor.setVendorName(payload.getVendorName());
            vendor.setAddress(payload.getAddress());
            vendor.setPhone(payload.getPhone());
            vendor.setGstNumber(payload.getGstNumber());
            vendor.setBusinessEmail(payload.getVendorEmail());
            vendorRepository.save(vendor);
            response.setMessage("Vendor profile edited successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setMessage("Failed to edit vendor profile :"+e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
