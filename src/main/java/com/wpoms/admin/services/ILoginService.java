package com.wpoms.admin.services;

import org.springframework.web.bind.annotation.RequestBody;

import com.wpoms.admin.models.payloads.RegisterManufacturerPayload;
import com.wpoms.admin.models.response.RegisterManufacturerResponse;

public interface ILoginService {

    RegisterManufacturerResponse registerManufacturer(RegisterManufacturerPayload payload)
;}
