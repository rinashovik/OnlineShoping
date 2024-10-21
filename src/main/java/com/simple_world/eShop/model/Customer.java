package com.simple_world.eShop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Customer extends AbstractEntity {

    @NotBlank
    @NotNull
    @Size(min = 2, max = 80, message = "Required field")
    private String firstName;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 80, message = "Required field")
    private String lastName;

    @NotBlank
    @NotNull
    @Email
    @Size(min = 2, max = 80, message = "Required field")
    private String contactEmail;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 80, message = "Required field")
    private String phoneNumber;
    @NotBlank
    @NotNull
    @Size(min = 2, max = 80, message = "Required field")
    private String address;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 80, message = "Required field")
    private String city;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 80, message = "Required field")
    private String zipCode;


    public Customer() {

    }

    public Customer(String firstName, String lastName, String contactEmail, String phoneNumber, String address, String city, String zipCode) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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

