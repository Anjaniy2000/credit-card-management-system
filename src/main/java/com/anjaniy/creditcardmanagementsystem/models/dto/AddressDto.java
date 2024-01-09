package com.anjaniy.creditcardmanagementsystem.models.dto;

import org.hibernate.validator.constraints.Length;

public class AddressDto {

    private String id;

    private String street;

    private String city;

    private String state;

    private String postal;

    @Length(min = 6, max = 6, message = "length of zipCode should be 6!")
    private Integer zip;

    public AddressDto() {
    }

    public AddressDto(
            String id,
            String street,
            String city,
            String state,
            String postal,
            Integer zip
    )
    {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.zip = zip;
    }

    public AddressDto(
            String street,
            String city,
            String state,
            String postal,
            Integer zip
    )
    {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.zip = zip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postal='" + postal + '\'' +
                ", zip=" + zip +
                '}';
    }

}
