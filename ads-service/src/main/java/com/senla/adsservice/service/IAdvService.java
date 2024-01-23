package com.senla.adsservice.service;

import com.senla.adsservice.dto.AdvCommentDto;
import com.senla.adsservice.dto.AdvDto;

import java.util.List;

public interface IAdvService {

    List<AdvDto> getAllSortedByParameter(String sortParameter, String sortOrder);

    void leaveComment(AdvCommentDto advCommentDto);

    List<AdvDto> getAllSellersAds();

    AdvDto getAdsById(long id);

    AdvDto addAds(AdvDto advDto);

    AdvDto modifyAdv(AdvDto advDto);

    void deleteAdv(long id);

    void payForAdvToTop(long advId);
}
