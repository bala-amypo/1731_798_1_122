package com.example.demo.service.impl;

import com.example.demo.model.DiscountCode;
import com.example.demo.model.Influencer;
import com.example.demo.model.Campaign;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.DiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiscountCodeServiceImpl implements DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;
    private final InfluencerRepository influencerRepository;
    private final CampaignRepository campaignRepository;

    @Autowired
    public DiscountCodeServiceImpl(DiscountCodeRepository discountCodeRepository,
                                   InfluencerRepository influencerRepository,
                                   CampaignRepository campaignRepository) {
        this.discountCodeRepository = discountCodeRepository;
        this.influencerRepository = influencerRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        if (discountCodeRepository.existsByCode(code.getCode())) {
            throw new RuntimeException("Discount code already exists");
        }

        Influencer influencer = influencerRepository.findById(code.getInfluencer().getId())
                .orElseThrow(() -> new RuntimeException("Influencer not found"));
        if (!influencer.isActive()) {
            throw new RuntimeException("Influencer is not active");
        }

        Campaign campaign = campaignRepository.findById(code.getCampaign().getId())
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        if (!campaign.isActive()) {
            throw new RuntimeException("Campaign is not active");
        }

        code.setInfluencer(influencer);
        code.setCampaign(campaign);
        code.setActive(true);
        
        if (code.getDiscountPercentage() == null || code.getDiscountPercentage() <= 0 || code.getDiscountPercentage() > 100) {
            throw new RuntimeException("Discount percentage must be between 0 and 100");
        }

        return discountCodeRepository.save(code);
    }

    @Override
    public DiscountCode updateDiscountCode(Long id, DiscountCode code) {
        DiscountCode existing = discountCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount code not found"));

        if (code.getCode() != null && !existing.getCode().equals(code.getCode())) {
            if (discountCodeRepository.existsByCode(code.getCode())) {
                throw new RuntimeException("Discount code already exists");
            }
            existing.setCode(code.getCode());
        }

        if (code.getDiscountPercentage() != null) {
            if (code.getDiscountPercentage() <= 0 || code.getDiscountPercentage() > 100) {
                throw new RuntimeException("Discount percentage must be between 0 and 100");
            }
            existing.setDiscountPercentage(code.getDiscountPercentage());
        }

        if (code.getActive() != null) {
            existing.setActive(code.getActive());
        }

        return discountCodeRepository.save(existing);
    }

    @Override
    public DiscountCode getCodeById(Long id) {
        return discountCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount code not found"));
    }

    @Override
    public List<DiscountCode> getCodesByInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencerId(influencerId);
    }

    @Override
    public List<DiscountCode> getCodesByCampaign(Long campaignId) {
        return discountCodeRepository.findByCampaignId(campaignId);
    }

    @Override
    public void deactivateCode(Long id) {
        DiscountCode code = discountCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount code not found"));
        code.setActive(false);
        discountCodeRepository.save(code);
    }

    @Override
    public List<DiscountCode> getAllDiscountCodes() {
        return discountCodeRepository.findAll();
    }
}