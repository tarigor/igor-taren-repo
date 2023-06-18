package com.senla.service.impl.product;

import com.senla.entity.ProductPart;
import com.senla.service.IProduct;

public class ProductImpl implements IProduct {

    private ProductPart bodyPart;
    private ProductPart chassisPart;
    private ProductPart enginePart;

    @Override
    public void installBodyPart(ProductPart bodyPart) {
        this.bodyPart = bodyPart;
        System.out.println("Body part installed");
    }

    @Override
    public void installChassisPart(ProductPart chassisPart) {
        this.chassisPart = chassisPart;
        System.out.println("Chassis part installed");
    }

    @Override
    public void installEnginePart(ProductPart enginePart) {
        this.enginePart = enginePart;
        System.out.println("Engine part installed");
    }

    @Override
    public String toString() {
        return "Car{" +
                "bodyPart=" + bodyPart +
                ", chassisPart=" + chassisPart +
                ", enginePart=" + enginePart +
                '}';
    }
}
