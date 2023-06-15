package com.senla.service.impl;

import com.senla.service.IProduct;
import com.senla.service.entity.Part;
import lombok.Data;

@Data
public class Product implements IProduct {
    private Part firstPart;
    private Part secondPart;
    private Part thirdPart;
}
