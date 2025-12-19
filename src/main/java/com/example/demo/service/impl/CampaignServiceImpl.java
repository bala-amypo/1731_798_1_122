package com.example.demo.service.impl;

import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.service.CampaignService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository repository;

    public CampaignServiceImpl(CampaignRepository repository) {
        this.repository = repository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        if (campaign.getStartDate().after(campaign.getEndDate())) {
            throw new RuntimeException("Invalid campaign date: Start date must be before end date");
        }
        if (repository.existsByCampaignName(campaign.getCampaignName())) {
            throw new RuntimeException("Campaign name already exists");
        }
        return repository.save(campaign);
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaign) {
        Campaign existing = getCampaignById(id);
        existing.setCampaignName(campaign.getCampaignName());
        existing.setBudget(campaign.getBudget());
        return repository.save(existing);
    }

    @Override
    public Campaign getCampaignById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id: " + id));
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return repository.findAll();
    }

    @Override
    public void deactivateCampaign(Long id) {
        Campaign campaign = getCampaignById(id);
        campaign.setActive(false);
        repository.save(campaign);
    }
}