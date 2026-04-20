package com.wpoms.admin.models.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorStaffPayload {
    @NotBlank(message = "Staff name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Vendor ID is required")
    private int vendorId;
}

