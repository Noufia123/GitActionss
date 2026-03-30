package com.wpoms.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wpoms.admin.models.entities.CustomerMaster;
@Repository

public interface CustomerRepository extends JpaRepository<CustomerMaster,Integer > {

    
} 
