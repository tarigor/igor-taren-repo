package com.senla;

import com.senla.entity.ProductPart;
import com.senla.service.impl.assembly.AssemblyLineImpl;
import com.senla.service.impl.linestep.LineStepBodyImpl;
import com.senla.service.impl.linestep.LineStepChassisImpl;
import com.senla.service.impl.linestep.LineStepEngineImpl;
import com.senla.service.impl.product.ProductImpl;
import com.senla.service.IProduct;

public class Main {
    public static void main(String[] args) {

        ProductPart bodyPart = new LineStepBodyImpl().buildProductPart();
        ProductPart chassisPart = new LineStepChassisImpl().buildProductPart();
        ProductPart enginePart = new LineStepEngineImpl().buildProductPart();

        AssemblyLineImpl assemblyLineImpl = new AssemblyLineImpl();

        assemblyLineImpl.prepareBodyPart(bodyPart);
        assemblyLineImpl.prepareChassisPart(chassisPart);
        assemblyLineImpl.prepareEnginePart(enginePart);

        IProduct car = new ProductImpl();

        System.out.println(assemblyLineImpl.assembleProduct(car));

    }
}