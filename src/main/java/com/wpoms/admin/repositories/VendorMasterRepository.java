package com.wpoms.admin.repositories;

import com.wpoms.admin.models.entities.VendorMaster;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorMasterRepository extends JpaRepository<VendorMaster, Integer> {

        boolean existsByGstNumber(@NotBlank(message = "Gst number is required") @Size(min = 15, max = 15, message = "GST number should contain 15 digits") String gstNumber);
        Optional<VendorMaster> findByUserId(Integer id);
        boolean existsByBusinessEmail(String vendorEmail);
        boolean existsByPhone(String phone);
        boolean existsByBusinessEmailAndUserIdNot(String vendorEmail, Integer id);
        boolean existsByGstNumberAndUserIdNot(String gstNumber, Integer id);
        boolean existsByPhoneAndUserIdNot(String phone, Integer id);
}
