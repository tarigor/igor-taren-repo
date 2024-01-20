package com.senla.adsweb.controller;

import com.senla.adsservice.dto.AdsDto;
import com.senla.adsservice.dto.AdvCommentDto;
import com.senla.adsservice.dto.AuthenticationRequestDto;
import com.senla.adsservice.dto.AuthenticationResponseDto;
import com.senla.adsservice.dto.UserDto;
import com.senla.adsservice.service.IAdvService;
import com.senla.adsservice.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/any")
public class AnyRightsController {

    private final IUserService userService;
    private final IAdvService advService;

    public AnyRightsController(IUserService userService, IAdvService advService) {
        this.userService = userService;
        this.advService = advService;
    }

    @PostMapping("/users")
    public UserDto userRegister(@RequestBody @Valid UserDto userDto) {
        return userService.userRegister(userDto);
    }

    @GetMapping("/users")
    public AuthenticationResponseDto userLogin(@RequestBody @Valid AuthenticationRequestDto authenticationRequestDto) {
        return userService.userLogin(authenticationRequestDto);
    }

    @GetMapping("/ads/{sortParameter}/{sortOrder}")
    public List<AdsDto> getAllAdsSorted(@PathVariable String sortParameter, @PathVariable String sortOrder) {
        return advService.getAllSortedByParameter(sortParameter, sortOrder);
    }

    @PostMapping("/ads/comment")
    public void leaveCommentToAds(@RequestBody AdvCommentDto advCommentDto) {
        advService.leaveComment(advCommentDto);
    }

    @GetMapping("/sellers/ads")
    public List<AdsDto> getAllSellersAds() {
        return advService.getAllSellersAds();
    }
}
