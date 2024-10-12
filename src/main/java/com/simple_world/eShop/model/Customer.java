package com.simple_world.eShop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Customer extends AbstractEntity {

   @NotBlank
   @NotNull
   @Size(min=2, max=80, message = "Required field")
   private String fName;

    @NotBlank
    @NotNull
    @Size(min=2, max=80, message = "Required field")
    private String lName;

    @NotBlank
    @NotNull
    @Size(min=2, max=80, message = "Required field")
    private String email;

    @NotBlank
    @NotNull
    @Size(min=2, max=80, message = "Required field")
    private String phoneNumber;
    @NotBlank
    @NotNull
    @Size(min=2, max=80, message = "Required field")
    private String address;

    @NotBlank
    @NotNull
    @Size(min=2, max=80, message = "Required field")
    private String city;

    @NotBlank
    @NotNull
    @Size(min=2, max=80, message = "Required field")
    private String zipCode;


    public Customer() {

    }
    public Customer(String fName, String lName, String email, String phoneNumber, String address, String city, String zipCode) {
        super();
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }




    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
