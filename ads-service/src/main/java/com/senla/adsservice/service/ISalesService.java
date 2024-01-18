package com.senla.adsservice.service;

import com.senla.adsservice.dto.SaleDto;

import java.util.List;

public interface ISalesService {
    List<SaleDto> getSaleHistoryByDate(String dateFrom, String dateTo);
}
