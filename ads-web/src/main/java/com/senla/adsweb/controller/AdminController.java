package com.senla.adsweb.controller;

import com.senla.adsservice.dto.OrderDto;
import com.senla.adsservice.dto.UserDto;
import com.senla.adsservice.service.IOrderService;
import com.senla.adsservice.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final IUserService userService;
    private final IOrderService orderService;

    public AdminController(IUserService userService, IOrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PutMapping("/users")
    public UserDto userModify(@RequestBody UserDto userDto) {
        return userService.userModify(userDto);
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping("/sellers")
    public List<UserDto> getSellers() {
        return userService.findSellers();
    }

    @GetMapping("/buyers")
    public List<UserDto> getBuyers() {
        return userService.findBuyers();
    }

    @GetMapping("/sales/{dateFrom}/{dateTo}")
    public List<OrderDto> getSalesHistory(@PathVariable String dateFrom, @PathVariable String dateTo) {
        return orderService.getSaleHistoryByDate(dateFrom, dateTo);
    }
}
