package com.senla.service.impl.linestep;

import com.senla.entity.ProductPart;
import com.senla.entity.BodyPart;
import com.senla.service.ILineStep;

public class LineStepBodyImpl implements ILineStep {
    @Override
    public ProductPart buildProductPart(){
        System.out.println("Body part built");
        return new BodyPart("Body");
    }
}
