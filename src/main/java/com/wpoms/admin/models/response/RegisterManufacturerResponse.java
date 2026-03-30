package com.wpoms.admin.models.response;

public class RegisterManufacturerResponse {

    private Long userId;
    private int manufacturerId;
    private String companyName;
    private String companyEmail;
    private String phone;
    
    private String message;


    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public int getManufacturerId() {
        return manufacturerId;
    }
    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyEmail() {
        return companyEmail;
    }
    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
