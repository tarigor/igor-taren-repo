package com.senla.entity;

public class Car {
    private ProductPart bodyPart;
    private ProductPart chassisPart;
    private ProductPart enginePart;

    public void setBodyPart(ProductPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public void setChassisPart(ProductPart chassisPart) {
        this.chassisPart = chassisPart;
    }

    public void setEnginePart(ProductPart enginePart) {
        this.enginePart = enginePart;
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
