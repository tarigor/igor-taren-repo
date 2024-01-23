package com.senla.adsservice.service.impl;

import com.senla.adsdatabase.entity.Correspondence;
import com.senla.adsdatabase.entity.User;
import com.senla.adsdatabase.repository.CorrespondenceRepository;
import com.senla.adsservice.dto.MessageToBuyerDto;
import com.senla.adsservice.dto.MessageToSellerDto;
import com.senla.adsservice.service.ICorrespondenceService;
import com.senla.adsservice.service.IUserService;
import com.senla.adsservice.util.EntityDtoMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CorrespondenceServiceImpl implements ICorrespondenceService {
    private final CorrespondenceRepository correspondenceRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final IUserService userService;

    public CorrespondenceServiceImpl(CorrespondenceRepository correspondenceRepository,
                                     EntityDtoMapper entityDtoMapper,
                                     IUserService userService) {
        this.correspondenceRepository = correspondenceRepository;
        this.entityDtoMapper = entityDtoMapper;
        this.userService = userService;
    }

    @Override
    public MessageToSellerDto sendMessageToSeller(MessageToSellerDto messageToSellerDto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User buyer = entityDtoMapper.convertFromDtoToEntity(userService.findUserByEmail(userName), User.class);
        User seller = entityDtoMapper.convertFromDtoToEntity(userService.getUser(messageToSellerDto.getSellerId()), User.class);
        Correspondence correspondence = correspondenceRepository.save(new Correspondence(buyer, seller, messageToSellerDto.getMessage()));
        return new MessageToSellerDto(correspondence.getId(), correspondence.getSeller().getId(), correspondence.getMessage());
    }

    @Override
    public MessageToBuyerDto sendMessageToBuyer(MessageToBuyerDto messageToBuyerDto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User seller = entityDtoMapper.convertFromDtoToEntity(userService.findUserByEmail(userName), User.class);
        User buyer = entityDtoMapper.convertFromDtoToEntity(userService.getUser(messageToBuyerDto.getBuyerId()), User.class);
        Correspondence correspondence = correspondenceRepository.save(new Correspondence(buyer, seller, messageToBuyerDto.getMessage()));
        return new MessageToBuyerDto(correspondence.getId(), correspondence.getBuyer().getId(), correspondence.getMessage());
    }
}
