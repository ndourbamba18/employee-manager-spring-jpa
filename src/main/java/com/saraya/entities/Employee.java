package com.saraya.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "phone")
        }
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    @NotNull
    @Size(max = 50)
    private String name;
    @NotNull
    @Size(max = 50)
    @Email
    @NaturalId
    private String email;
    @NotNull
    @Size(max = 16)
    private String phone;
    @NotNull
    private String department;
    private String baseLocation;
    private String address;

    public Employee() {}

    public Employee(String name, String email, String phone, String department, String baseLocation, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.baseLocation = baseLocation;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

	@Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", baseLocation='" + baseLocation + '\'' +
                '}';
    }
}
