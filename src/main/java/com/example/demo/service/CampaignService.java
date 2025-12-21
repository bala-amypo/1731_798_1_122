package com.example.demo.service;

import com.example.demo.model.Campaign;

import java.util.List;

public interface CampaignService {
    Campaign updateCampaign(Long campaignId, Campaign campaign);
    Campaign getCampaignById(Long campaignId);
    List<Campaign> getAllCampaigns();
}