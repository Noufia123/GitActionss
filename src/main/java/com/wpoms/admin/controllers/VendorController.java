package com.wpoms.admin.controllers;

import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import com.wpoms.admin.services.IVendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    IVendorService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterVendorResponse> addVendor(@Valid @RequestBody RegisterVendorPayload payload){
        return service.registerVendor(payload);
    }

    @GetMapping("/getVendor")
    public ResponseEntity<RegisterVendorResponse> getVendor(@RequestParam Integer id){
        return service.getVendor(id);
    }
}
