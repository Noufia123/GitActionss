package com.wpoms.admin.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorStaffResponse {
    private int sId;
    private String name;
    private String phone;
    private String department;
    private int vendorId;
}
