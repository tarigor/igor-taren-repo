package com.senla.hotel.entity;

import lombok.Data;

@Data
public class Guest {
    private long id;
    private String firstName;
    private String lastName;

    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
