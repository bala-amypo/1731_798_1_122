package com.example.demo.service;

import com.example.demo.model.DiscountCode;

import java.util.List;

public interface DiscountCodeService {
    DiscountCode getDiscountCodeById(Long id);
    DiscountCode updateDiscountCode(Long id, DiscountCode updated);
    List<DiscountCode> getCodesForInfluencer(Long influencerId);
    List<DiscountCode> getCodesForCampaign(Long campaignId);
}