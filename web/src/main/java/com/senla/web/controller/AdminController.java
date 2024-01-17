package com.senla.web.controller;

import com.senla.adsservice.dto.SaleDto;
import com.senla.adsservice.dto.UserDto;
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
    @PutMapping("/users")
    public UserDto userModify(@RequestBody UserDto userDto) {
        return null;
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable int id) {
        return null;
    }

    @GetMapping("/sellers")
    public List<UserDto> getSellers() {
        return null;
    }

    @GetMapping("/buyers")
    public List<UserDto> getBuyers() {
        return null;
    }

    @GetMapping("/sales/{dateFrom}/{dateTo}")
    public List<SaleDto> getSalesHistory(@PathVariable String dateFrom, @PathVariable String dateTo) {
        return null;
    }
}
