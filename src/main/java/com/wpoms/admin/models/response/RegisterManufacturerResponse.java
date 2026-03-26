package com.wpoms.admin.models.response;

public class RegisterManufacturerResponse {

    private Long userId;
    private Long manufacturerId;
    private String message;


    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getManufacturerId() {
        return manufacturerId;
    }
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
