package com.senla.adsservice.service.impl;

import com.senla.adsdatabase.repository.AdvertisementRepository;
import com.senla.adsservice.dto.AdsDto;
import com.senla.adsservice.service.IAdvService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdsServiceImpl implements IAdvService {

    private final AdvertisementRepository advertisementRepository;

    public AdsServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public List<AdsDto> getAllSortedByParameter(String sortParameter) {
        return null;
    }
}
