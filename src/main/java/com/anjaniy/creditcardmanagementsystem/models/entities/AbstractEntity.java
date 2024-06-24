package com.anjaniy.creditcardmanagementsystem.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class AbstractEntity <T extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

}
