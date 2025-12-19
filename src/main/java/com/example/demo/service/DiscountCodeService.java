package com.example.demo.service;

import com.example.demo.model.DiscountCode;
import com.example.demo.model.Influencer;
import com.example.demo.model.Campaign;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;
    private final InfluencerRepository influencerRepository;
    private final CampaignRepository campaignRepository;

    @Autowired
    public DiscountCodeService(DiscountCodeRepository discountCodeRepository,
                               InfluencerRepository influencerRepository,
                               CampaignRepository campaignRepository) {
        this.discountCodeRepository = discountCodeRepository;
        this.influencerRepository = influencerRepository;
        this.campaignRepository = campaignRepository;
    }

    public DiscountCode createDiscountCode(DiscountCode code) {
        // Check if influencer exists and is active
        Influencer influencer = influencerRepository.findById(code.getInfluencer().getId())
                .orElseThrow(() -> new RuntimeException("Influencer not found"));
        if (!influencer.isActive()) {
            throw new RuntimeException("Influencer is not active");
        }

        // Check if campaign exists and is active
        Campaign campaign = campaignRepository.findById(code.getCampaign().getId())
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        if (!campaign.isActive()) {
            throw new RuntimeException("Campaign is not active");
        }

        // Check if code already exists
        if (discountCodeRepository.existsByCode(code.getCode())) {
            throw new RuntimeException("Discount code already exists");
        }

        // Set active by default
        code.setActive(true);
        return discountCodeRepository.save(code);
    }

    public DiscountCode updateDiscountCode(Long id, DiscountCode code) {
        DiscountCode existing = discountCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount code not found"));

        // Update fields
        if (code.getDiscountPercentage() != null) {
            existing.setDiscountPercentage(code.getDiscountPercentage());
        }
        if (code.getActive() != null) {
            existing.setActive(code.getActive());
        }

        return discountCodeRepository.save(existing);
    }

    public DiscountCode getCodeById(Long id) {
        return discountCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount code not found"));
    }

    public List<DiscountCode> getCodesByInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencerId(influencerId);
    }

    public List<DiscountCode> getCodesByCampaign(Long campaignId) {
        return discountCodeRepository.findByCampaignId(campaignId);
    }

    public void deactivateCode(Long id) {
        DiscountCode code = discountCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount code not found"));
        code.setActive(false);
        discountCodeRepository.save(code);
    }

    public List<DiscountCode> getAllDiscountCodes() {
        return discountCodeRepository.findAll();
    }
}