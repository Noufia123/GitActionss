package com.wpoms.admin.models.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditManufacturerPayload {
   @NotBlank(message = "Company Email is required")
    private String companyEmail;
  @NotBlank(message = "Company name is required")
  private String companyName;
  @NotBlank(message = "Company Address is required")
  private String companyAddress;
   @NotBlank(message = "Phone is required")
       @Pattern(
            regexp = "^\\+?[1-9]\\d{10,14}$",
            message = "Phone must be a valid international number"
    )
    
    private String companyPhone;
    @NotBlank(message = "Gst number is required")
    @Size(min = 15,max = 15,message = "GST number should contain 15 digits")
    private String gstNumber;


    

}
