package com.senla.service.impl;

import com.senla.service.ILineStep;
import com.senla.service.IProductPart;

public class LineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart(String firstPartName,String secondPartName,String thirdPartName) {
        return new ProductPart();
    }
}
