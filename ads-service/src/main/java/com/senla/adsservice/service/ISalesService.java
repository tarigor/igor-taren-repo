package com.senla.adsservice.service;

import com.senla.database.entity.Sale;

import java.util.Date;
import java.util.List;

public interface ISalesService {
    List<Sale> getSalesHistory(Date dateFrom, Date dateTo);

    Sale getSale(int saleId);

    Sale recordSale(Sale sale);

    List<Sale> getSales();
}
