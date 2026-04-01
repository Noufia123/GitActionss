package com.wpoms.admin.controllers;
import com.wpoms.admin.models.payloads.EditVendorPayload;
import com.wpoms.admin.models.payloads.RegisterVendorPayload;
import com.wpoms.admin.models.response.EditVendorResponse;
import com.wpoms.admin.models.response.RegisterVendorResponse;
import com.wpoms.admin.services.IVendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
@CrossOrigin
public class VendorController {
    @Autowired
    IVendorService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterVendorResponse> addVendor(@Valid @RequestBody RegisterVendorPayload payload){
        RegisterVendorResponse response= service.registerVendor(payload);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<RegisterVendorResponse> getVendor(@RequestParam Integer id){
        RegisterVendorResponse response= service.getVendor(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<EditVendorResponse> editVendor(@RequestParam Integer id, @Valid @RequestBody EditVendorPayload payload){
        EditVendorResponse response= service.editVendor(id,payload);
        return ResponseEntity.ok(response);
    }
}
