package com.senla.adsservice.service;

import com.senla.adsservice.dto.AdsDto;
import com.senla.adsservice.dto.AdvCommentDto;

import java.util.List;

public interface IAdvService {

    List<AdsDto> getAllSortedByParameter(String sortParameter, String sortOrder);

    void leaveComment(AdvCommentDto advCommentDto);

    List<AdsDto> getAllSellersAds();
}
