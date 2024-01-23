package com.senla.adsweb.controller;

import com.senla.adsservice.dto.AdvDto;
import com.senla.adsservice.dto.MessageToBuyerDto;
import com.senla.adsservice.dto.OrderDto;
import com.senla.adsservice.service.IAdvService;
import com.senla.adsservice.service.ICorrespondenceService;
import com.senla.adsservice.service.IOrderService;
import jakarta.validation.Valid;
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
    private final IAdvService advService;
    private final IOrderService orderService;
    private final ICorrespondenceService correspondenceService;

    public SellerController(IAdvService advService, IOrderService orderService, ICorrespondenceService correspondenceService) {
        this.advService = advService;
        this.orderService = orderService;
        this.correspondenceService = correspondenceService;
    }

    @PostMapping("/adv")
    public AdvDto addAdv(@RequestBody @Valid AdvDto advDto) {
        return advService.addAds(advDto);
    }

    @PutMapping("/adv")
    public AdvDto editeAdv(@RequestBody @Valid AdvDto advDto) {
        return advService.modifyAdv(advDto);
    }

    @DeleteMapping("/adv/{id}")
    public void deleteAdv(@PathVariable long id) {
        advService.deleteAdv(id);
    }

    @PostMapping("/adv/top/{advId}")
    public void payForAdvToTop(@PathVariable long advId) {
        advService.payForAdvToTop(advId);
    }

    @GetMapping("/sales")
    public List<OrderDto> getSales() {
        return orderService.getSales();
    }

    @PostMapping("/buyer/message")
    public MessageToBuyerDto sendMessageToBuyer(@RequestBody @Valid MessageToBuyerDto messageToBuyerDto) {
        return correspondenceService.sendMessageToBuyer(messageToBuyerDto);
    }
}
