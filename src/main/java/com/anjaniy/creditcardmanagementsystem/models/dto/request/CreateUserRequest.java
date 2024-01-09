package com.anjaniy.creditcardmanagementsystem.models.dto.request;

import com.anjaniy.creditcardmanagementsystem.models.dto.AddressDto;
import com.anjaniy.creditcardmanagementsystem.models.dto.CreditCardDto;
import com.anjaniy.creditcardmanagementsystem.models.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import java.util.List;

public class CreateUserRequest {

    @NotBlank(message = "name is required!")
    private String name;

    @NotBlank(message = "email is required!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Length(min = 10, max = 10, message = "length of phoneNumber should be 10")
    @NotBlank(message = "phoneNumber is required!")
    private String phoneNumber;

    @NotNull(message = "gender is required!")
    private Gender gender;

    @NotNull(message = "address is required!")
    private AddressDto address;

    @NotNull(message = "creditCards are required!")
    private List<CreditCardDto> creditCards;

    public CreateUserRequest() {
    }

    public CreateUserRequest(
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
        return "CreateUserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", creditCards=" + creditCards +
                '}';
    }

}
