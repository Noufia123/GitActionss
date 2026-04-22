package com.wpoms.admin.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wpoms.admin.models.entities.VendorStaff;

@Repository
public interface VendorStaffRepository extends JpaRepository<VendorStaff, Integer> {

    Optional<VendorStaff> findByUserId(Long userId);

    List<VendorStaff> findByVendorId(int vendorId);
}