// CampaignService.java
package com.example.demo.service;

import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;
    
    public Campaign createCampaign(Campaign campaign) {
        // Validate dates
        if (campaign.getEndDate().isBefore(campaign.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        return campaignRepository.save(campaign);
    }
    
    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }
    
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }
    
    public Campaign updateCampaign(Long id, Campaign campaignDetails) {
        Campaign campaign = getCampaignById(id);
        campaign.setCampaignName(campaignDetails.getCampaignName());
        campaign.setStartDate(campaignDetails.getStartDate());
        campaign.setEndDate(campaignDetails.getEndDate());
        return campaignRepository.save(campaign);
    }
}