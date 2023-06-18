package com.senla.service.impl.assembly;

import com.senla.entity.ProductPart;
import com.senla.service.IAssembleProduct;
import com.senla.service.IProduct;

public class AssemblyLineImpl implements IAssembleProduct {
    private ProductPart bodyPart;
    private ProductPart chassisPart;
    private ProductPart enginePart;

    public void prepareBodyPart(ProductPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public void prepareChassisPart(ProductPart chassisPart) {
        this.chassisPart = chassisPart;
    }

    public void prepareEnginePart(ProductPart enginePart) {
        this.enginePart = enginePart;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        product.installBodyPart(bodyPart);
        product.installChassisPart(chassisPart);
        product.installEnginePart(enginePart);
        return product;
    }


}
