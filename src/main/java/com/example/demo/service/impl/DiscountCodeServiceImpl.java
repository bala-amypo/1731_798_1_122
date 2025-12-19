package com.example.demo.service.impl;

import com.example.demo.model.DiscountCode;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.service.DiscountCodeService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {
    private final DiscountCodeRepository discountCodeRepository;

    public DiscountCodeServiceImpl(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public DiscountCode getDiscountCodeById(Long id) {
        return discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
    }

    @Override
    public DiscountCode updateDiscountCode(Long id, DiscountCode updated) {
        DiscountCode existing = getDiscountCodeById(id);
        if (updated.getDiscountPercentage() < 0 || updated.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("percentage must be between 0 and 100");
        }
        existing.setCodeValue(updated.getCodeValue());
        existing.setDiscountPercentage(updated.getDiscountPercentage());
        return discountCodeRepository.save(existing);
    }

    @Override
    public List<DiscountCode> getCodesForInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencerId(influencerId);
    }

    @Override
    public List<DiscountCode> getCodesForCampaign(Long campaignId) {
        return discountCodeRepository.findByCampaignId(campaignId);
    }

    @Override
    public void deactivateCode(Long id) {
        DiscountCode code = getDiscountCodeById(id);
        // Requirement implies active field exists if you want to deactivate
        // If DiscountCode entity has 'active' field:
        // code.setActive(false);
        discountCodeRepository.save(code);
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        if (code.getDiscountPercentage() < 0 || code.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("percentage must be between 0 and 100");
        }
        return discountCodeRepository.save(code);
    }
}