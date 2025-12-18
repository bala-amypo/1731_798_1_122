package com.example.demo.service.impl;

import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.CampaignService;

import java.math.BigDecimal;
import java.util.List;

public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    // 🚨 EXACT constructor signature
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        campaignRepository.findByCampaignName(campaign.getCampaignName())
                .ifPresent(c -> {
                    throw new RuntimeException("Campaign already exists");
                });

        if (campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Budget must be non-negative");
        }

        if (campaign.getStartDate().after(campaign.getEndDate())) {
            throw new RuntimeException("Invalid date range");
        }

        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaign) {
        Campaign existing = getCampaignById(id);
        existing.setCampaignName(campaign.getCampaignName());
        existing.setStartDate(campaign.getStartDate());
        existing.setEndDate(campaign.getEndDate());
        existing.setBudget(campaign.getBudget());
        return campaignRepository.save(existing);
    }

    @Override
    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public void deactivateCampaign(Long id) {
        Campaign campaign = getCampaignById(id);
        campaign.setActive(false);
        campaignRepository.save(campaign);
    }
}
