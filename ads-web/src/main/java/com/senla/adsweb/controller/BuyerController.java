package com.senla.adsweb.controller;

import com.senla.adsservice.dto.MessageToSellerDto;
import com.senla.adsservice.dto.OrderDto;
import com.senla.adsservice.service.ICorrespondenceService;
import com.senla.adsservice.service.IOrderService;
import com.senla.adsservice.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
    private final IOrderService orderService;
    private final ICorrespondenceService correspondenceService;

    public BuyerController(OrderServiceImpl orderService,
                           ICorrespondenceService correspondenceService) {
        this.orderService = orderService;
        this.correspondenceService = correspondenceService;
    }

    @PostMapping("/orders")
    public OrderDto orderAds(@RequestBody OrderDto orderDto) {
        return orderService.orderAds(orderDto);
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping("/seller/message")
    public MessageToSellerDto sendMessageToSeller(@RequestBody MessageToSellerDto messageToSellerDto) {
        return correspondenceService.sendMessageToSeller(messageToSellerDto);
    }
}
