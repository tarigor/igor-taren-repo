package com.senla;

import com.senla.entity.Car;
import com.senla.entity.ProductPart;
import com.senla.service.impl.assembly.AssemblyLineImpl;
import com.senla.service.impl.linestep.LineStepBodyImpl;
import com.senla.service.impl.linestep.LineStepChassisImpl;
import com.senla.service.impl.linestep.LineStepEngineImpl;

public class Main {
    public static void main(String[] args) {

        ProductPart bodyPart = new LineStepBodyImpl().buildProductPart();
        ProductPart chassisPart = new LineStepChassisImpl().buildProductPart();
        ProductPart enginePart = new LineStepEngineImpl().buildProductPart();

        AssemblyLineImpl assemblyLineImpl = new AssemblyLineImpl();

        assemblyLineImpl.installBodyPart(bodyPart);
        assemblyLineImpl.installChassisPart(chassisPart);
        assemblyLineImpl.installEnginePart(enginePart);

        Car car = new Car();

        assemblyLineImpl.assembleProduct(car);
    }
}