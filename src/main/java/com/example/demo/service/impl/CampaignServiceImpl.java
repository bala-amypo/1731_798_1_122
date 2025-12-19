package com.example.demo.service.impl;

import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        if (campaignRepository.existsByCampaignName(campaign.getCampaignName())) {
            throw new RuntimeException("Campaign name already exists");
        }

        if (campaign.getStartDate().after(campaign.getEndDate())) {
            throw new RuntimeException("Start date must be before end date");
        }

        if (campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Budget must be non-negative");
        }

        campaign.setActive(true);
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaign) {
        Campaign existing = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        if (campaign.getCampaignName() != null && !existing.getCampaignName().equals(campaign.getCampaignName())) {
            if (campaignRepository.existsByCampaignName(campaign.getCampaignName())) {
                throw new RuntimeException("Campaign name already exists");
            }
            existing.setCampaignName(campaign.getCampaignName());
        }
        
        if (campaign.getStartDate() != null) {
            existing.setStartDate(campaign.getStartDate());
        }
        if (campaign.getEndDate() != null) {
            existing.setEndDate(campaign.getEndDate());
        }
        
        // Validate dates if both are being updated
        if (campaign.getStartDate() != null && campaign.getEndDate() != null) {
            if (campaign.getStartDate().after(campaign.getEndDate())) {
                throw new RuntimeException("Start date must be before end date");
            }
        } else if (campaign.getStartDate() != null && existing.getEndDate() != null) {
            if (campaign.getStartDate().after(existing.getEndDate())) {
                throw new RuntimeException("Start date must be before end date");
            }
        } else if (existing.getStartDate() != null && campaign.getEndDate() != null) {
            if (existing.getStartDate().after(campaign.getEndDate())) {
                throw new RuntimeException("Start date must be before end date");
            }
        }
        
        if (campaign.getBudget() != null) {
            if (campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Budget must be non-negative");
            }
            existing.setBudget(campaign.getBudget());
        }
        
        if (campaign.getActive() != null) {
            existing.setActive(campaign.getActive());
        }

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
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.setActive(false);
        campaignRepository.save(campaign);
    }
}