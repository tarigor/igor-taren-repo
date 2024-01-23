package com.senla.adsservice.service;

import com.senla.adsservice.dto.MessageToBuyerDto;
import com.senla.adsservice.dto.MessageToSellerDto;

public interface ICorrespondenceService {

    MessageToSellerDto sendMessageToSeller(MessageToSellerDto messageToSellerDto);

    MessageToBuyerDto sendMessageToBuyer(MessageToBuyerDto messageToBuyerDto);
}
