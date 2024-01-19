package com.senla.adsweb.controller;

import com.senla.adsservice.dto.SaleDto;
import com.senla.adsservice.dto.UserDto;
import com.senla.adsservice.service.ISalesService;
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
    private final ISalesService salesService;

    public AdminController(IUserService userService, ISalesService salesService) {
        this.userService = userService;
        this.salesService = salesService;
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
    public List<SaleDto> getSalesHistory(@PathVariable String dateFrom, @PathVariable String dateTo) {
        return salesService.getSaleHistoryByDate(dateFrom, dateTo);
    }
}
