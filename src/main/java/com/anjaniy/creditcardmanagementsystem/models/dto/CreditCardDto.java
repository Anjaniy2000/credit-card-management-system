package com.anjaniy.creditcardmanagementsystem.models.dto;

import org.hibernate.validator.constraints.Length;

public class CreditCardDto {

    private String id;

    @Length(min = 6, max = 6, message = "length if ccNumber should be 6")
    private Long ccNumber;

    private String expiryDate;

    private Double creditLimit;

    public CreditCardDto() {

    }

    public CreditCardDto(
            String id,
            Long ccNumber,
            String expiryDate,
            Double creditLimit
    )
    {
        this.id = id;
        this.ccNumber = ccNumber;
        this.expiryDate = expiryDate;
        this.creditLimit = creditLimit;
    }

    public CreditCardDto(
            Long ccNumber,
            String expiryDate,
            Double creditLimit
    )
    {
        this.ccNumber = ccNumber;
        this.expiryDate = expiryDate;
        this.creditLimit = creditLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(Long ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return "CreditCardDto{" +
                "id=" + id +
                ", ccNumber=" + ccNumber +
                ", expiryDate='" + expiryDate + '\'' +
                ", creditLimit=" + creditLimit +
                '}';
    }

}
