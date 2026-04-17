package com.wpoms.admin.models.payloads;
import lombok.Data;
@Data

public class ManufacturerStaffPayload {

    private String name;
    private String phone;
    private String department;
    private String email;
    private String password;
    private int manufacturerId;
    
}
