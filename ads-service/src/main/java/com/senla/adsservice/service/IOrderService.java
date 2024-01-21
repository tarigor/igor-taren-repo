package com.senla.adsservice.service;

import com.senla.adsservice.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    List<OrderDto> getSaleHistoryByDate(String dateFrom, String dateTo);

    OrderDto orderAds(OrderDto orderDto);

    List<OrderDto> getOrders();
}
