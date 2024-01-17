package com.senla.web.controller;

import com.senla.adsservice.dto.MessageToSellerDto;
import com.senla.adsservice.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
    @PostMapping("/orders")
    public OrderDto orderAds(@RequestBody OrderDto orderDto) {
        return null;
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        return null;
    }

    @PostMapping("/seller/message")
    public void sendMessageToSeller(@RequestBody MessageToSellerDto messageToSellerDto) {

    }
}
