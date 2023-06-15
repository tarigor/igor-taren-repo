package com.senla.service;

public interface IProductPart {
    void installFirstPart(IProductPart productPart);
    void installSecondPart(IProductPart productPart);
    void installThirdPart(IProductPart productPart);
}
