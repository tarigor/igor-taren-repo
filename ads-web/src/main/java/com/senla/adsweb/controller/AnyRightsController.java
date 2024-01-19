package com.senla.adsweb.controller;

import com.senla.adsservice.dto.AdsDto;
import com.senla.adsservice.dto.CommentDto;
import com.senla.adsservice.dto.UserDto;
import com.senla.adsservice.enums.AdsSortParameters;
import com.senla.adsservice.service.IAdvService;
import com.senla.adsservice.service.IUserService;
import com.senla.adsservice.validator.annotation.EnumValidator;
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

    @GetMapping("/ads/{sortParameter}")
    public List<AdsDto> getAllAdsSorted(@PathVariable @EnumValidator(targetClassType = AdsSortParameters.class) String sortParameter) {
        return advService.getAllSortedByParameter(sortParameter);
    }

    @PostMapping("/ads/comment")
    public void leaveCommentToAds(@RequestBody CommentDto commentDto) {
    }

    @GetMapping("/sellers/ads")
    public List<AdsDto> getAllSellersAds() {
        return null;
    }
}
