package com.anjaniy.creditcardmanagementsystem.utilities;

import com.anjaniy.creditcardmanagementsystem.models.dto.AddressDto;
import com.anjaniy.creditcardmanagementsystem.models.dto.CreditCardDto;
import com.anjaniy.creditcardmanagementsystem.models.dto.UserDto;
import com.anjaniy.creditcardmanagementsystem.models.entities.Address;
import com.anjaniy.creditcardmanagementsystem.models.entities.CreditCard;
import com.anjaniy.creditcardmanagementsystem.models.entities.User;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Mapper {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static UserDto convert(User user) {
        UserDto userDto = new UserDto(user.getName(), user.getEmail(), user.getPhoneNumber(), user.getGender(), Mapper.convert(user.getAddress()), user.getCreditCards().stream().map(Mapper::convert).collect(Collectors.toList()));
        if(user.getId() != null){
            userDto.setId(user.getId().toString());
        }
        return userDto;
    }

    public static User convert(UserDto userDto) {
        User user = null;
        if(userDto.getId() != null) {
            user = new User(UUID.fromString(userDto.getId()), userDto.getName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getGender());
        } else {
            user = new User(UUID.randomUUID(), userDto.getName(), userDto.getEmail(), userDto.getPhoneNumber(), userDto.getGender());
        }
        user.setAddress(Mapper.convert(userDto.getAddress(), user));
        user.setCreditCards(Mapper.convert(userDto.getCreditCards(), user));
        return user;
    }

    public static AddressDto convert(Address address) {
        AddressDto addressDto = new AddressDto(address.getStreet(), address.getCity(), address.getState(), address.getPostal(), address.getZip());
        if(address.getId() != null) {
            addressDto.setId(String.valueOf(address.getId()));
        }
        return addressDto;
    }

    public static Address convert(AddressDto addressDto, User user) {
        Address address = null;
        if(addressDto.getId() != null) {
            address = new Address(UUID.fromString(addressDto.getId()), addressDto.getStreet(), addressDto.getCity(), addressDto.getState(), addressDto.getPostal(), addressDto.getZip(), user);
        } else {
            address = new Address(UUID.randomUUID(), addressDto.getStreet(), addressDto.getCity(), addressDto.getState(), addressDto.getPostal(), addressDto.getZip(), user);
        }
        return address;
    }

    public static CreditCardDto convert(CreditCard creditCard) {
        CreditCardDto creditCardDto = new CreditCardDto(creditCard.getCcNumber(), creditCard.getExpiryDate().toString(), creditCard.getCreditLimit());
        if(creditCard.getId() != null) {
            creditCardDto.setId(String.valueOf(creditCard.getId()));
        }

        return creditCardDto;
    }

    public static List<CreditCard> convert(List<CreditCardDto> creditCardDtos, User user) {
        List<CreditCard> creditCards = new ArrayList<>();
        Date expiryDate = null;
        for(CreditCardDto creditCardDto : creditCardDtos) {
            if(creditCardDto.getId() != null) {
                try {
                    expiryDate = new Date(simpleDateFormat.parse(creditCardDto.getExpiryDate()).getTime());
                    creditCards.add(new CreditCard(UUID.fromString(creditCardDto.getId()), creditCardDto.getCcNumber(), expiryDate, creditCardDto.getCreditLimit(), user));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    expiryDate = new Date(simpleDateFormat.parse(creditCardDto.getExpiryDate()).getTime());
                    creditCards.add(new CreditCard(UUID.randomUUID(), creditCardDto.getCcNumber(), expiryDate, creditCardDto.getCreditLimit(), user));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return creditCards;
    }

    public static CreditCard convert(CreditCardDto creditCardDto, User user) {
        CreditCard creditCard = null;
        Date expiryDate = null;
        if(creditCardDto.getId() != null || (!creditCardDto.getId().isEmpty())) {
            try {
                expiryDate = new Date(simpleDateFormat.parse(creditCardDto.getExpiryDate()).getTime());
                creditCard = new CreditCard(UUID.fromString(creditCardDto.getId()), creditCardDto.getCcNumber(), expiryDate, creditCardDto.getCreditLimit(), user);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                expiryDate = new Date(simpleDateFormat.parse(creditCardDto.getExpiryDate()).getTime());
                creditCard = new CreditCard(UUID.randomUUID(), creditCardDto.getCcNumber(), expiryDate, creditCardDto.getCreditLimit(), user);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return creditCard;
    }

    public void greet(){
        System.out.println("Hello World");
    }

    public static Float getPI(){
        return 3.14F;
    }

    public Integer square(int number){
        return number * number;
    }

    public void greet(String name) {
        System.out.println("Good morning " + name);
    }

    public void temp(){
        System.out.println("Nice");
    }
}
