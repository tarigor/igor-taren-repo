package com.senla.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private ProductPart bodyPart;
    private ProductPart chassisPart;
    private ProductPart enginePart;
}
