package com.senla.service;

import com.senla.entity.ProductPart;

public interface IProduct {
    void installBodyPart(ProductPart bodyPart);

    void installChassisPart(ProductPart chassisPart);

    void installEnginePart(ProductPart enginePart);
}
