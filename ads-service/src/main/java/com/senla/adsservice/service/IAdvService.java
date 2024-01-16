package com.senla.adsservice.service;

import com.senla.database.entity.Ads;

import java.util.List;

public interface IAdvService {
    int addAds(Ads ads);

    int editeAds(Ads ads);

    void deleteAds(int adsId);

    List<Ads> getSellerAds(int sellerId);

    void leaveCommentToAds(int adsId, String commentText);

    void payForAdsPriority(int adsId);
}
