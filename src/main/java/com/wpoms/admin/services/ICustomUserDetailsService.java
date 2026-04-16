package com.wpoms.admin.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface ICustomUserDetailsService extends UserDetailsService {
    // You can add custom methods here if needed
    // For now, it extends Spring's UserDetailsService
}