package com.saraya.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeDto {

    @NotBlank
    @Size(max = 50)
    private String name;
    @NotBlank
    @Size(max = 50)
    private String email;
    @NotBlank
    @Size(max = 16)
    private String phone;
    @NotBlank
    private String department;
    private String baseLocation;
    private String address;

    public EmployeeDto() {}

    public EmployeeDto(String name, String email, String phone, String department, String baseLocation, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.baseLocation = baseLocation;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
