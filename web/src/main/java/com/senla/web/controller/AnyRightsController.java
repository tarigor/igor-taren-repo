package com.senla.web.controller;

import com.senla.adsservice.dto.AdsDto;
import com.senla.adsservice.dto.CommentDto;
import com.senla.adsservice.dto.UserDto;
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
    @PostMapping("/users")
    public UserDto userRegister(@RequestBody UserDto userDto) {
        return null;
    }

    @GetMapping("/ads/{sortParameter}")
    public List<AdsDto> getAllAdsSorted(@PathVariable String sortParameter) {
        return null;
    }

    @PostMapping("/ads/comment")
    public void leaveCommentToAds(@RequestBody CommentDto commentDto) {
    }

    @GetMapping("/sellers/ads")
    public List<AdsDto> getAllSellersAds() {
        return null;
    }

}
