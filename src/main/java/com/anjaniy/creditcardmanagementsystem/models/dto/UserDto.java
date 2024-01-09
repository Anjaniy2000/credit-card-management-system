package com.anjaniy.creditcardmanagementsystem.models.dto;

import com.anjaniy.creditcardmanagementsystem.models.enums.Gender;
import java.util.List;

public class UserDto {

    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private Gender gender;

    private AddressDto address;

    private List<CreditCardDto> creditCards;

    public UserDto() {

    }

    public UserDto(
            String id,
            String name,
            String email,
            String phoneNumber,
            Gender gender,
            AddressDto address,
            List<CreditCardDto> creditCards
    )
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.creditCards = creditCards;
    }

    public UserDto(
            String name,
            String email,
            String phoneNumber,
            Gender gender,
            AddressDto address,
            List<CreditCardDto> creditCards
    )
    {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.creditCards = creditCards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<CreditCardDto> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardDto> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", creditCards=" + creditCards +
                '}';
    }

}
