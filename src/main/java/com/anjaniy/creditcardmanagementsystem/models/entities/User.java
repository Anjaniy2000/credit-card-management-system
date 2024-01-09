package com.anjaniy.creditcardmanagementsystem.models.entities;

import com.anjaniy.creditcardmanagementsystem.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table(name = "user_table")
@Entity
public class User extends AbstractEntity<UUID> {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private String phoneNumber;

    @Column(name = "gender")
    private Gender gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CreditCard> creditCards;


    public User() {

    }

    public User(@NotNull UUID id,
                @NotNull String name,
                @NotNull String email,
                @NotNull String phoneNumber,
                @NotNull Gender gender,
                @NotNull Address address,
                @NotNull List<CreditCard> creditCards
    )
    {
        setId(id);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.creditCards = creditCards;
    }

    public User(@NotNull UUID id,
                @NotNull String name,
                @NotNull String email,
                @NotNull String phoneNumber,
                @NotNull Gender gender
    )
    {
        setId(id);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", creditCards=" + creditCards +
                '}';
    }

}
