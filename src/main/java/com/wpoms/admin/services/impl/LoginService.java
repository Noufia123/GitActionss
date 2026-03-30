// package com.wpoms.admin.services.impl;

// import java.time.LocalDateTime;

// import org.apache.catalina.User;
// import org.springframework.stereotype.Service;

// import com.wpoms.admin.models.entities.CustomerMaster;
// import com.wpoms.admin.models.entities.UserMaster;
// import com.wpoms.admin.models.payloads.RegisterCustomerPayload;
// import com.wpoms.admin.models.payloads.RegisterManufacturerPayload;
// import com.wpoms.admin.models.response.RegisterCustomerResponse;
// import com.wpoms.admin.models.response.RegisterManufacturerResponse;
// import com.wpoms.admin.repositories.UserMasterRepository;
// import com.wpoms.admin.services.ILoginService;

// @Service
// public class LoginService implements ILoginService{


//     private UserMasterRepository _userMasterRepository;


//     public LoginService(UserMasterRepository _userMasterRepository) {
//         this._userMasterRepository = _userMasterRepository;
//     }




//     @Override
//     public RegisterManufacturerResponse registerManufacturer(RegisterManufacturerPayload payload) {

//         RegisterManufacturerResponse response = new RegisterManufacturerResponse();
//         try {

//             UserMaster user = new UserMaster();
//             user.setEmail(payload.getEmail());
//             user.setPasswordHash(payload.getPassword());
//             user.setRole(payload.getRole());
//             user.setCreatedAt(LocalDateTime.now());
//             user.setUpdatedAt(LocalDateTime.now());
//             user.setActive(true);

//             _userMasterRepository.save(user);

//             response.setUserId(user.getId());
//             response.setMessage("Manufacturer registered successfully");
//         } catch (Exception ex) {
//             System.out.println(ex.getMessage());
//             response.setMessage("Error registering manufacturer - " + ex.getMessage());
//         }

//         return response;
//     }


// }
