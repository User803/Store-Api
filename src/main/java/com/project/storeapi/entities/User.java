package com.project.storeapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column( name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Address is the owner of this relationship because in the DB design Users Table has no column for Address but Addresses table has user_id column. Owner is the side with the foreign key
    @JsonManagedReference
    private List<Address> addresses = new ArrayList<>();

    @ManyToMany // OneToMany & ManyToMany relationships are Lazy loaded by default
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    ) // No need to have user variable in Product class as it isn't required. No need to show user who else has this product in their wishlist
    @JsonIgnore
    private Set<Product> favoriteProducts = new HashSet<>();

    @OneToOne(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true) // Profile is the owner of the relationship. It has a reference column to User
    @JsonManagedReference("profile")
    private Profile profile;

//    @ManyToMany // OneToMany & ManyToMany relationships are Lazy loaded by default
//    @JoinTable(
//            name = "user_tags",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    ) // This is a Many-to-Many relationship so JoinTable(Has a separate table(user_tags) for this relationship) + JoinColumn, and User is the owner of the relationship
//    private Set<Tag> tags = new HashSet<>();

    public User() { }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addFavoriteProduct(Product product) {
        favoriteProducts.add(product);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

//    public void addTag(String tagName) {
//        var tag = new Tag(tagName);
//        tags.add(tag);
//        tag.getUsers().add(this);
//    }
//
//    public void removeTag(String tagName) {
//        tags.stream()
//            .filter(tag -> tag.getName().equals(tagName))
//            .findFirst()
//            .ifPresent(tags::remove);
//    }

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(Set<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
