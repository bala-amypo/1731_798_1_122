package com.example.demo.service;

import com.example.demo.model.DiscountCode;
import java.util.List;

public interface DiscountCodeService {
    DiscountCode createDiscountCode(DiscountCode code);
    DiscountCode updateDiscountCode(Long id, DiscountCode updated);
    DiscountCode getDiscountCodeById(Long id); // Corrected name
    List<DiscountCode> getCodesForInfluencer(Long influencerId); // Corrected name
    List<DiscountCode> getCodesForCampaign(Long campaignId); // Corrected name
    void deactivateCode(Long id);
}