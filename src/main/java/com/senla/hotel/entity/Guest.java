package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Guest {
    private long id;
    private String firstName;
    private String lastName;

}
