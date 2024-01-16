package com.senla.adsservice.service;

public interface ICorrespondenceService {
    int sendMessageFromBuyerToSeller(int sellerId, int buyerId, String text);

    int sendMessageFromSellerToBuyer(int sellerId, int buyerId, String text);
}
