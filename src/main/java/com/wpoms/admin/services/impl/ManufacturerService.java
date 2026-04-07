package com.wpoms.admin.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wpoms.admin.models.entities.ManufacturerMaster;
import com.wpoms.admin.models.entities.UserMaster;
import com.wpoms.admin.models.payloads.EditManufacturerPayload;
import com.wpoms.admin.models.payloads.RegisterManufacturerPayload;
import com.wpoms.admin.models.response.EditManufacturerResponse;
import com.wpoms.admin.models.response.RegisterManufacturerResponse;
import com.wpoms.admin.repositories.ManufacturerMasterRepository;
import com.wpoms.admin.repositories.UserMasterRepository;
import com.wpoms.admin.services.IManufacturerService;

@Service
public class ManufacturerService implements IManufacturerService {

    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private ManufacturerMasterRepository manufacturerMasterRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public RegisterManufacturerResponse registerManufacturer(RegisterManufacturerPayload payload) {

        RegisterManufacturerResponse response = new RegisterManufacturerResponse();

        if (manufacturerMasterRepository.existsByCompanyEmail(payload.getCompanyEmail())) {
            throw new RuntimeException("Company email already exists");
        }

        if (manufacturerMasterRepository.existsByGstNumber(payload.getGstNumber().toUpperCase())) {
            throw new RuntimeException("GST number already exists");
        }

        if (userMasterRepository.existsByEmail(payload.getEmail())) {
            throw new RuntimeException("User email already exists");

        }
        if (manufacturerMasterRepository.existsByPhone(payload.getCompanyPhone())) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        UserMaster user = new UserMaster();
        user.setEmail(payload.getEmail());
        user.setPasswordHash(bCryptPasswordEncoder.encode(payload.getPassword()));
        user.setRole(payload.getRole());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setActive(true);

        userMasterRepository.save(user);

        response.setMessage("Manufacturer registered successfully");

        ManufacturerMaster manufacturer = new ManufacturerMaster();
        manufacturer.setCompanyName(payload.getCompanyName());
        manufacturer.setCompanyEmail(payload.getCompanyEmail());
        manufacturer.setAddress(payload.getCompanyAddress());
        manufacturer.setPhone(payload.getCompanyPhone());
        manufacturer.setGstNumber(payload.getGstNumber());
        manufacturer.setUserId(user.getId());

        manufacturerMasterRepository.save(manufacturer);
        response.setUserId(user.getId());
        response.setManufacturerId(manufacturer.getManufacturerId());
        response.setCompanyName(manufacturer.getCompanyName());
        response.setCompanyEmail(manufacturer.getCompanyEmail());
        response.setAddress(manufacturer.getAddress());
        response.setPhone(manufacturer.getPhone());
        response.setGstNumber(manufacturer.getGstNumber().toUpperCase());
        response.setMessage("Manufacturer registered successfully");

        return response;
    }

    @Override
    public RegisterManufacturerResponse getManufacturerById(int id) {

        RegisterManufacturerResponse response = new RegisterManufacturerResponse();

        ManufacturerMaster manufacture = manufacturerMasterRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
        response.setManufacturerId(manufacture.getManufacturerId());
        
        // response.setUserId(manufacture.getUserId());
        response.setCompanyEmail(manufacture.getCompanyEmail());
        response.setCompanyName(manufacture.getCompanyName());
        response.setPhone(manufacture.getPhone());
        response.setAddress(manufacture.getAddress());
        response.setGstNumber(manufacture.getGstNumber());
        response.setMessage("Manufacturer fetched successfully");

        return response;
    }

    @Override
    public EditManufacturerResponse updateManufacture(int id, EditManufacturerPayload payload) {

        EditManufacturerResponse response = new EditManufacturerResponse();

        if (manufacturerMasterRepository
                .existsByCompanyEmailAndUserIdNot(payload.getCompanyEmail(), id)) {
            throw new RuntimeException("Company email already exists for another manufacturer");
        }

        if (manufacturerMasterRepository
                .existsByGstNumberAndUserIdNot(payload.getGstNumber().toUpperCase(), id)) {
            throw new RuntimeException("GST number already exists for another manufacturer");
        }

        if (manufacturerMasterRepository.existsByPhoneAndUserIdNot(
                payload.getCompanyPhone(), id)) {
            throw new IllegalArgumentException("Phone number already exists for another manufacturer");
        }
        ManufacturerMaster manufacturerMaster = manufacturerMasterRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));

        manufacturerMaster.setCompanyName(payload.getCompanyName());
        manufacturerMaster.setCompanyEmail(payload.getCompanyEmail());
        manufacturerMaster.setAddress(payload.getCompanyAddress());
        manufacturerMaster.setPhone(payload.getCompanyPhone());
        manufacturerMaster.setGstNumber(payload.getGstNumber().toUpperCase());

        manufacturerMasterRepository.save(manufacturerMaster);

        response.setCompanyEmail(manufacturerMaster.getCompanyEmail());
        response.setCompanyName(manufacturerMaster.getCompanyName());
        response.setPhone(manufacturerMaster.getPhone());
        response.setGstNumber(manufacturerMaster.getGstNumber());
        response.setMessage("Manufacturer updated successfully");

        return response;
    }
}