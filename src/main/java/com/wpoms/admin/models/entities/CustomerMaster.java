package com.wpoms.admin.models.entities;

import java.time.LocalDate;

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
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "contact_preference")
    private Long contactPreference;

}
