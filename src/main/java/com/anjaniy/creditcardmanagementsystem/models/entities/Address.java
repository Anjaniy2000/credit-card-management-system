package com.anjaniy.creditcardmanagementsystem.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Table(name = "address_table")
@Entity
public class Address extends AbstractEntity<UUID> {

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal")
    private String postal;

    @Column(name = "zip")
    private Integer zip;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User user;

    public Address(){

    }

    public Address(
            @NotNull UUID id,
            @NotNull String street,
            @NotNull String city,
            @NotNull String state,
            @NotNull String postal,
            @NotNull Integer zip,
            @NotNull User user
    )
    {
        setId(id);
        this.street = street;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.zip = zip;
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + getId() +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postal='" + postal + '\'' +
                ", zip=" + zip +
                ", user=" + user +
                '}';
    }

}
