package com.senla.web.controller;

import com.senla.adsservice.dto.AdsDto;
import com.senla.adsservice.dto.MessageToBuyerDto;
import com.senla.adsservice.dto.SaleDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @PostMapping("/ads")
    public AdsDto addAds(@RequestBody AdsDto adsDto) {
        return null;
    }

    @PutMapping("/ads")
    public AdsDto editeAds(@RequestBody AdsDto adsDto) {
        return null;
    }

    @DeleteMapping("/ads/{id}")
    public void deleteAds(@PathVariable long id) {

    }

    @PostMapping("/ads/top/{id}")
    public void payForAdsToTop(@PathVariable long id) {

    }

    @GetMapping("/sales")
    public List<SaleDto> getSales() {
        return null;
    }

    @PostMapping("/buyer/message")
    public void sendMessageToBuyer(@RequestBody MessageToBuyerDto messageToBuyerDto) {

    }
}
