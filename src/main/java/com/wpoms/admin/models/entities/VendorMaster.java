package com.wpoms.admin.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vendors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name= "vendor_id")
    private int vendorId;

    @Column(name="user_id")
    private int userId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="company_email")
    private String companyEmail;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private long phone;

    @Column(name="gst_number")
    private String gstNumber;

}
