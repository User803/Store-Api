package com.project.storeapi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;

    public Profile() { }

    public Profile(String bio, String phoneNumber, LocalDate dateOfBirth, Integer loyaltyPoints) {
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.loyaltyPoints = loyaltyPoints;
    }

    // Even if fetch is set to lazy, it only affects Profile but User will still fetch profile eagerly
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id") // Profile knows about the User but User isn't aware of Profile Table (Profile Table has foreign key). So Profile is the owner thus @JoinColumn
    @MapsId // Tells Hibernate to use the same column as a primary and foreign key of the entity
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", loyaltyPoints=" + loyaltyPoints +
                ", user=" + user +
                '}';
    }
}
