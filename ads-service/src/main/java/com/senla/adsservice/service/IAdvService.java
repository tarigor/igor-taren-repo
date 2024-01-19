package com.senla.adsservice.service;

import com.senla.adsservice.dto.AdsDto;

import java.util.List;

public interface IAdvService {

    List<AdsDto> getAllSortedByParameter(String sortParameter);
}
