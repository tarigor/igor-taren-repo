package com.senla.service.impl.product;

import com.senla.entity.ProductPart;

public abstract class Product {
    public abstract void installBodyPart(ProductPart bodyPart);

    public abstract void installChassisPart(ProductPart chassisPart);

    public abstract void installEnginePart(ProductPart enginePart);
}
