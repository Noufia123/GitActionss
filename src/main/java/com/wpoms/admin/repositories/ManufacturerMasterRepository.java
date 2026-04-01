package com.wpoms.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wpoms.admin.models.entities.ManufacturerMaster;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Repository
public interface ManufacturerMasterRepository extends JpaRepository<ManufacturerMaster, Integer> {

    boolean existsByCompanyEmail(String companyEmail);

    boolean existsByCompanyEmailAndManufacturerIdNot(String companyEmail, Integer manufacturerId);

    boolean existsByGstNumber(String gstNumber);

    boolean existsByGstNumberAndManufacturerIdNot(String gstNumber, Integer manufacturerId);
    boolean existsByPhone(String phone);
    boolean existsByPhoneAndManufacturerIdNot(String phone, Integer manufacturerId);


}


