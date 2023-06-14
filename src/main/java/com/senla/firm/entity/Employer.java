package com.senla.firm.entity;

import com.senla.firm.constant.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employer {
    private String firstName;
    private String lastName;
    private Position position;
    private double salary;
}
