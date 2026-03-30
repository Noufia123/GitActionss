package com.wpoms.admin.services;



import com.wpoms.admin.models.payloads.RegisterManufacturerPayload;
import com.wpoms.admin.models.response.RegisterManufacturerResponse;

public interface IManufacturerService {

    RegisterManufacturerResponse registerManufacturer(RegisterManufacturerPayload payload);

    RegisterManufacturerResponse  getManufacturerById(int id);
    RegisterManufacturerResponse  updateManufacture(int id , RegisterManufacturerPayload payload);
}


