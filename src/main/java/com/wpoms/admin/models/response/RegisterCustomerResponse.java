package com.wpoms.admin.models.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterCustomerResponse {
    private Integer customerId;
    private Integer userId;
    private String message;

    private String customerName;
    private String customerEmail;
    private String phoneNo;
    private LocalDate dateOfBirth;
    private String shippingAddress;
    private String contactPreference;

}
