package com.senla.adsservice.service.impl;

import com.senla.adsdatabase.entity.Adv;
import com.senla.adsdatabase.entity.AdvComment;
import com.senla.adsdatabase.repository.AdvCommentRepository;
import com.senla.adsdatabase.repository.AdvRepository;
import com.senla.adsdatabase.repository.OrderRepository;
import com.senla.adsdatabase.repository.SellerRatingRepository;
import com.senla.adsservice.dto.AdsDto;
import com.senla.adsservice.dto.AdvCommentDto;
import com.senla.adsservice.enums.AdsSortParameters;
import com.senla.adsservice.enums.Ordering;
import com.senla.adsservice.service.IAdvService;
import com.senla.adsservice.util.EntityDtoMapper;
import com.senla.adsservice.validator.annotation.EnumValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdsServiceImpl implements IAdvService {

    private final AdvRepository advRepository;
    private final SellerRatingRepository sellerRatingRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final AdvCommentRepository advCommentRepository;
    private final OrderRepository orderRepository;

    public AdsServiceImpl(AdvRepository advRepository,
                          SellerRatingRepository sellerRatingRepository,
                          EntityDtoMapper entityDtoMapper,
                          AdvCommentRepository advCommentRepository,
                          OrderRepository orderRepository) {
        this.advRepository = advRepository;
        this.sellerRatingRepository = sellerRatingRepository;
        this.entityDtoMapper = entityDtoMapper;
        this.advCommentRepository = advCommentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<AdsDto> getAllSortedByParameter(@EnumValidator(targetClassType = AdsSortParameters.class) String adsSortParameter,
                                                @EnumValidator(targetClassType = Ordering.class) String sortOrder) {
        return advRepository.findAll().stream()
                .sorted(getComparator(AdsSortParameters.valueOf(adsSortParameter), Ordering.valueOf(sortOrder)))
                .map(ads -> entityDtoMapper.convertFromEntityToDto(ads, AdsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void leaveComment(AdvCommentDto advCommentDto) {
        advCommentRepository.save(entityDtoMapper.convertFromDtoToEntity(advCommentDto, AdvComment.class));
    }

    @Override
    public List<AdsDto> getAllSellersAds() {
        return advRepository.findAll().stream()
                .map(a -> entityDtoMapper.convertFromEntityToDto(a, AdsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdsDto getAdsById(long id) {
        return entityDtoMapper.convertFromEntityToDto(
                advRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementException("there is no such a ads with id -> " + id)), AdsDto.class);
    }

    private Comparator<? super Adv> getComparator(AdsSortParameters adsSortParameter, Ordering sortOrder) {
        Comparator<Adv> comparator = null;
        switch (adsSortParameter) {
            case SELLER_NAME -> comparator = Comparator.comparing(a -> a.getSeller().getCompanyName());
            case PRIORITY -> comparator = Comparator.comparing(Adv::getPriority);
            case SELLER_RATING -> comparator = Comparator.comparing(
                    a -> sellerRatingRepository.findById(a.getSeller().getId())
                            .orElseThrow(() -> new NoSuchElementException("there is no such a user with id->" + a.getSeller().getId()))
                            .getRating());
            default -> log.error("An ordering by section -> {} is not possible", adsSortParameter.name());
        }
        if (sortOrder == Ordering.DESC) {
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
