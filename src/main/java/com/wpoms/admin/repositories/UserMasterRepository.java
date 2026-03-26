package com.wpoms.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wpoms.admin.models.entities.UserMaster;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

}
