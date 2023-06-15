package com.senla.service.entity;

import com.senla.service.Test;
import lombok.Data;

@Data
public class Car {
    private Part body;
    private Part chassis;
    private Part engine;
}
