package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DiscountCode;
import com.example.demo.model.Influencer;
import com.example.demo.model.Campaign;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.service.DiscountCodeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCodeServiceImpl implements DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;
    private final InfluencerRepository influencerRepository;
    private final CampaignRepository campaignRepository;
    private final SaleTransactionRepository saleTransactionRepository;

    public DiscountCodeServiceImpl(DiscountCodeRepository discountCodeRepository,
                                  InfluencerRepository influencerRepository,
                                  CampaignRepository campaignRepository,
                                  SaleTransactionRepository saleTransactionRepository) {
        this.discountCodeRepository = discountCodeRepository;
        this.influencerRepository = influencerRepository;
        this.campaignRepository = campaignRepository;
        this.saleTransactionRepository = saleTransactionRepository;
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        // Check for duplicate code
        if (discountCodeRepository.existsByCode(code.getCode())) {
            throw new RuntimeException("Duplicate discount code");
        }
        
        // Validate influencer exists and is active
        Influencer influencer = influencerRepository.findById(code.getInfluencer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found"));
        if (!influencer.getActive()) {
            throw new IllegalArgumentException("Influencer is not active");
        }
        
        // Validate campaign exists and is active
        Campaign campaign = campaignRepository.findById(code.getCampaign().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
        if (!campaign.getActive()) {
            throw new IllegalArgumentException("Campaign is not active");
        }
        
        // Validate discount percentage
        if (code.getDiscountPercentage() < 0 || code.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        
        // Ensure active is true by default
        if (code.getActive() == null) {
            code.setActive(true);
        }
        
        // Set the actual entities
        code.setInfluencer(influencer);
        code.setCampaign(campaign);
        
        return discountCodeRepository.save(code);
    }

    @Override
    public DiscountCode updateDiscountCode(Long id, DiscountCode code) {
        DiscountCode existingCode = discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
        
        // Check if code is being changed to an existing one
        if (code.getCode() != null && 
            !code.getCode().equals(existingCode.getCode()) &&
            discountCodeRepository.existsByCode(code.getCode())) {
            throw new RuntimeException("Duplicate discount code");
        }
        
        // Update fields if provided
        if (code.getCode() != null) {
            existingCode.setCode(code.getCode());
        }
        if (code.getDiscountPercentage() != null) {
            if (code.getDiscountPercentage() < 0 || code.getDiscountPercentage() > 100) {
                throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
            }
            existingCode.setDiscountPercentage(code.getDiscountPercentage());
        }
        if (code.getActive() != null) {
            existingCode.setActive(code.getActive());
        }
        
        return discountCodeRepository.save(existingCode);
    }

    @Override
    public DiscountCode getCodeById(Long id) {
        return discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
    }

    @Override
    public List<DiscountCode> getCodesByInfluencer(Long influencerId) {
        // Verify influencer exists
        influencerRepository.findById(influencerId)
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found"));
        
        return discountCodeRepository.findByInfluencerId(influencerId);
    }

    @Override
    public List<DiscountCode> getCodesByCampaign(Long campaignId) {
        // Verify campaign exists
        campaignRepository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
        
        return discountCodeRepository.findByCampaignId(campaignId);
    }

    @Override
    public DiscountCode deactivateCode(Long id) {
        DiscountCode code = getCodeById(id);
        code.setActive(false);
        return discountCodeRepository.save(code);
    }
}