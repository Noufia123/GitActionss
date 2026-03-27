package com.wpoms.admin.repositories;

import com.wpoms.admin.models.entities.VendorMaster;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorMasterRepository extends JpaRepository<VendorMaster,Integer> {


    boolean existsByBusinessEmail(@NotBlank(message = "Email is required") @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Vendor email must be valid"
    ) String vendorEmail);
}
