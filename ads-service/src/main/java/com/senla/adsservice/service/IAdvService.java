package com.senla.adsservice.service;

import com.senla.database.entity.Advertisement;

import java.util.List;

public interface IAdvService {
    int addAds(Advertisement advertisement);

    int editeAds(Advertisement advertisement);

    void deleteAds(int adsId);

    List<Advertisement> getSellerAds(int sellerId);

    void leaveCommentToAds(int adsId, String commentText);

    void payForAdsPriority(int adsId);
}
