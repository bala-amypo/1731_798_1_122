// DiscountCodeService.java
package com.example.demo.service;

import com.example.demo.model.DiscountCode;
import com.example.demo.repository.DiscountCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscountCodeService {
    @Autowired
    private DiscountCodeRepository discountCodeRepository;
    
    public DiscountCode createDiscountCode(DiscountCode discountCode) {
        // Validate percentage
        if (discountCode.getDiscountPercentage() < 0 || discountCode.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        return discountCodeRepository.save(discountCode);
    }
    
    public DiscountCode getDiscountCodeById(Long id) {
        return discountCodeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Discount code not found"));
    }
    
    public DiscountCode updateDiscountCode(Long id, DiscountCode discountCodeDetails) {
        DiscountCode discountCode = getDiscountCodeById(id);
        discountCode.setCodeValue(discountCodeDetails.getCodeValue());
        discountCode.setDiscountPercentage(discountCodeDetails.getDiscountPercentage());
        return discountCodeRepository.save(discountCode);
    }
    
    public List<DiscountCode> getCodesForInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencerId(influencerId);
    }
    
    public List<DiscountCode> getCodesForCampaign(Long campaignId) {
        return discountCodeRepository.findByCampaignId(campaignId);
    }
}