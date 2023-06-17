package com.senla.entity;

public abstract class ProductPart {
    public String partName;

    public ProductPart(String partName) {
        this.partName = partName;
    }

    @Override
    public String toString() {
        return "ProductPart{" +
                "partName='" + partName + '\'' +
                '}';
    }
}
