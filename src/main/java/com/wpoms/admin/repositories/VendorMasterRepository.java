package com.wpoms.admin.repositories;

import com.wpoms.admin.models.entities.VendorMaster;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorMasterRepository extends JpaRepository<VendorMaster,Integer> {


    boolean existsByBusinessEmail(@NotBlank(message = "Email is required") @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Vendor email must be valid"
    ) String vendorEmail);

    boolean existsByBusinessEmailAndVendorIdNot(@NotBlank(message = "Email is required") @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Vendor email must be valid"
    ) String vendorEmail, Integer id);

    boolean existsByGstNumber(@NotBlank(message = "Gst number is required") @Size(min = 15,max = 15,message = "GST number should contain 15 digits") String gstNumber);

    boolean existsByGstNumberAndVendorIdNot(@NotBlank(message = "Gst number is required") @Size(min = 15,max = 15,message = "GST number should contain 15 digits") String gstNumber, Integer id);

    boolean existsByPhone(@NotBlank(message = "Phone number is required") @Pattern(
            regexp = "^\\+?[1-9]\\d{10,14}$",
            message = "Phone must be a valid international number"
    ) String phone);

    boolean existsByPhoneAndVendorIdNot(@NotBlank(message = "Phone number is required") @Pattern(
            regexp = "^\\+?[1-9]\\d{10,14}$",
            message = "Phone must be a valid international number"
    ) String phone, Integer id);
}
