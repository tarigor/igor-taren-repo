package com.senla.service.impl.assembly;

import com.senla.entity.Car;
import com.senla.entity.ProductPart;
import com.senla.service.IAssembleProduct;
import com.senla.service.IProduct;

public class AssemblyLineImpl implements IProduct, IAssembleProduct {
    private ProductPart bodyPart;
    private ProductPart chassisPart;
    private ProductPart enginePart;

    @Override
    public Car assembleProduct(Car car) {
        car.setBodyPart(bodyPart);
        car.setChassisPart(chassisPart);
        car.setEnginePart(enginePart);
        System.out.println("new Car has been built -> " + car);
        return car;
    }

    @Override
    public void installBodyPart(ProductPart bodyPart) {
        this.bodyPart = bodyPart;
        System.out.println("body part installed");
    }

    @Override
    public void installChassisPart(ProductPart chassisPart) {
        this.chassisPart = chassisPart;
        System.out.println("chassis part installed");
    }

    @Override
    public void installEnginePart(ProductPart enginePart) {
        this.enginePart = enginePart;
        System.out.println("engine part installed");
    }
}
