package com.example.demo.service;

import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign createCampaign(Campaign campaign) {
        // Check if campaign name already exists
        if (campaignRepository.existsByCampaignName(campaign.getCampaignName())) {
            throw new RuntimeException("Campaign name already exists");
        }

        // Validate dates
        if (campaign.getStartDate().after(campaign.getEndDate())) {
            throw new RuntimeException("Start date must be before end date");
        }

        // Validate budget
        if (campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Budget must be non-negative");
        }

        // Set active by default
        campaign.setActive(true);
        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaign(Long id, Campaign campaign) {
        Campaign existing = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        // Update fields
        if (campaign.getCampaignName() != null) {
            existing.setCampaignName(campaign.getCampaignName());
        }
        if (campaign.getStartDate() != null) {
            existing.setStartDate(campaign.getStartDate());
        }
        if (campaign.getEndDate() != null) {
            existing.setEndDate(campaign.getEndDate());
        }
        if (campaign.getBudget() != null) {
            existing.setBudget(campaign.getBudget());
        }
        if (campaign.getActive() != null) {
            existing.setActive(campaign.getActive());
        }

        return campaignRepository.save(existing);
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public void deactivateCampaign(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.setActive(false);
        campaignRepository.save(campaign);
    }
}