package com.wpoms.admin.models.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manufacturers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerMaster {

    @Column(name="user_id")
    private int userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private int manufacturerId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="company_email")
    private String companyEmail;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "gst_number")
    private String gstNumber;
}
