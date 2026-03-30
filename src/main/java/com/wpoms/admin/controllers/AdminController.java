// package com.wpoms.admin.controllers;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.wpoms.admin.models.payloads.RegisterManufacturerPayload;
// import com.wpoms.admin.models.response.RegisterManufacturerResponse;
// import com.wpoms.admin.services.ILoginService;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;


// @RestController
// @RequestMapping("/api/admin")
// public class AdminController {

//     private final ILoginService loginService;

//     public AdminController(ILoginService loginService) {
//         this.loginService = loginService;
//     }

//     @PostMapping("/register-manufacturer")
//     public RegisterManufacturerResponse registerManufacturer(@RequestBody RegisterManufacturerPayload payload) {
       
//         RegisterManufacturerResponse response = loginService.registerManufacturer(payload);
//         return response;
//     }
    

// }
