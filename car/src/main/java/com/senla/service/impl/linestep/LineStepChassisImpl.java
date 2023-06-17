package com.senla.service.impl.linestep;

import com.senla.entity.ProductPart;
import com.senla.entity.ChassisPart;
import com.senla.service.ILineStep;

public class LineStepChassisImpl implements ILineStep {
    @Override
    public ProductPart buildProductPart(){
        System.out.println("Chassis part built");
        return new ChassisPart("Chassis");
    }
}
