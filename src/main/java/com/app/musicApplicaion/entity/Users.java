package com.app.musicApplicaion.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required!")
    @Size(min = 1, message = "Name cannot be empty!")
    private String name;

    @NotNull(message = "Email is required!")
    @Size(min = 1, message = "Email cannot be empty!")
    @Email(message = "Invalid email format!")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password is required!")
    @Size(min = 1, max = 8, message = "Password must be between 1 and 8 characters!")
    private String password;

    @NotNull(message = "Gender is required!")
    @Size(min = 1, message = "Gender cannot be empty!")
    private String gender;

    @NotNull(message = "Role is required!")
    @Size(min = 1, message = "Role cannot be empty!")
    private String role;

    @NotNull(message = "Address is required!")
    @Size(min = 1, message = "Address cannot be empty!")
    private String address;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isPremium;

    public Users() {
        // Default constructor
    }

    public Users(Long id, String name, String email, String password, String gender, String role, String address, boolean isPremium) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.address = address;
        this.isPremium = isPremium;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", gender=" + gender +
                ", role=" + role + ", address=" + address + ", isPremium=" + isPremium + "]";
    }

    // Override equals and hashCode if needed
}
