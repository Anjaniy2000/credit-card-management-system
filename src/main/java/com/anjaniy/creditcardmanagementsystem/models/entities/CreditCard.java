package com.anjaniy.creditcardmanagementsystem.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.UUID;

@Table(name = "credit_card_table")
@Entity
public class CreditCard extends AbstractEntity<UUID> {

    @Column(name = "cc_number")
    private Long ccNumber;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "credit_limit")
    private Double creditLimit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User user;

    public CreditCard() {

    }

    public CreditCard(
            @NotNull UUID id,
            @NotNull Long ccNumber,
            @NotNull Date expiryDate,
            @NotNull Double creditLimit,
            @NotNull User user
    )
    {
        setId(id);
        this.ccNumber = ccNumber;
        this.expiryDate = expiryDate;
        this.creditLimit = creditLimit;
        this.user = user;
    }

    public Long getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(Long ccNumber) {
        this.ccNumber = ccNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + getId() +
                ", ccNumber=" + ccNumber +
                ", expiryDate='" + expiryDate + '\'' +
                ", creditLimit=" + creditLimit +
                ", user=" + user +
                '}';
    }

}
