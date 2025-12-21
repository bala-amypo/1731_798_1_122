package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.CampaignService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign updateCampaign(Long campaignId, Campaign campaign) {
        Campaign existingCampaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
        
        // Validate date range
        if (campaign.getEndDate() != null && campaign.getStartDate() != null) {
            if (campaign.getEndDate().isBefore(campaign.getStartDate())) {
                throw new IllegalArgumentException("End date must not be earlier than start date");
            }
        }
        
        // Update fields
        if (campaign.getCampaignName() != null) {
            existingCampaign.setCampaignName(campaign.getCampaignName());
        }
        if (campaign.getStartDate() != null) {
            existingCampaign.setStartDate(campaign.getStartDate());
        }
        if (campaign.getEndDate() != null) {
            existingCampaign.setEndDate(campaign.getEndDate());
        }
        
        return campaignRepository.save(existingCampaign);
    }

    @Override
    public Campaign getCampaignById(Long campaignId) {
        return campaignRepository.findById(campaignId)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }
}