package com.senla.service.impl.linestep;

import com.senla.entity.EnginePart;
import com.senla.entity.ProductPart;
import com.senla.service.ILineStep;

public class LineStepEngineImpl implements ILineStep {
    @Override
    public ProductPart buildProductPart() {
        System.out.println("Engine part built");
        return new EnginePart("Engine");
    }
}
