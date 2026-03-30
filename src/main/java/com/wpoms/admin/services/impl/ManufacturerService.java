package com.wpoms.admin.services.impl;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpoms.admin.models.entities.ManufacturerMaster;
import com.wpoms.admin.models.entities.UserMaster;
import com.wpoms.admin.models.payloads.RegisterManufacturerPayload;
import com.wpoms.admin.models.response.RegisterManufacturerResponse;
import com.wpoms.admin.repositories.ManufacturerMasterRepository;
import com.wpoms.admin.repositories.UserMasterRepository;
import com.wpoms.admin.services.IManufacturerService;

@Service
public class ManufacturerService implements IManufacturerService{


    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
     private ManufacturerMasterRepository manufacturerMasterRepository;




    @Override
    public RegisterManufacturerResponse registerManufacturer(RegisterManufacturerPayload payload) {

        RegisterManufacturerResponse response = new RegisterManufacturerResponse();
        try {

            UserMaster user = new UserMaster();
            user.setEmail(payload.getEmail());
            user.setPasswordHash(payload.getPassword());
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
              response.setPhone(manufacturer.getPhone());
               response.setMessage("Manufacturer registered successfully");

            

        } 
        
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.setMessage("Error registering manufacturer - " + ex.getMessage());
        }




        return response;
    }

 @Override
  public RegisterManufacturerResponse  getManufacturerById(int id){

    RegisterManufacturerResponse response = new RegisterManufacturerResponse();
    try{
        ManufacturerMaster manufacture = manufacturerMasterRepository.findById(id)  .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
        response.setManufacturerId(manufacture.getManufacturerId());
        response.setUserId(manufacture.getUserId());
        response.setCompanyEmail(manufacture.getCompanyEmail());
        response.setCompanyName(manufacture.getCompanyName());
        response.setPhone(manufacture.getPhone());
        response.setMessage("Manufacturer fetched successfully");

    }
    catch (Exception ex){
        System.out.println(ex.getMessage());
        response.setMessage("Error :" +ex.getMessage());
    }


      return response;
}
@Override
public RegisterManufacturerResponse updateManufacture(int id, RegisterManufacturerPayload payload) {

    RegisterManufacturerResponse response = new RegisterManufacturerResponse();

    try {
        // Correct way to fetch
        ManufacturerMaster manufacturerMaster = manufacturerMasterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));

        manufacturerMaster.setCompanyName(payload.getCompanyName());
        manufacturerMaster.setCompanyEmail(payload.getCompanyEmail());
        manufacturerMaster.setAddress(payload.getCompanyAddress());
        manufacturerMaster.setPhone(payload.getCompanyPhone());
        manufacturerMaster.setGstNumber(payload.getGstNumber());

        manufacturerMasterRepository.save(manufacturerMaster);

       
        response.setManufacturerId(manufacturerMaster.getManufacturerId());
        response.setUserId(manufacturerMaster.getUserId());
        response.setCompanyEmail(manufacturerMaster.getCompanyEmail());
        response.setCompanyName(manufacturerMaster.getCompanyName());
        response.setPhone(manufacturerMaster.getPhone());
        response.setMessage("Manufacturer updated successfully");

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        response.setMessage("Error in updation: " + ex.getMessage());
    }

    return response; 
}
}