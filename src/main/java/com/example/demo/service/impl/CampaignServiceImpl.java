// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.Campaign;
// import com.example.demo.repository.CampaignRepository;
// import com.example.demo.service.CampaignService;
// import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.util.List;

// @Service
// public class CampaignServiceImpl implements CampaignService {

//     private final CampaignRepository campaignRepository;

//     public CampaignServiceImpl(CampaignRepository campaignRepository) {
//         this.campaignRepository = campaignRepository;
//     }

//     @Override
//     public Campaign createCampaign(Campaign campaign) {
//         // Check for duplicate campaign name
//         if (campaignRepository.existsByCampaignName(campaign.getCampaignName())) {
//             throw new RuntimeException("Duplicate campaign name");
//         }
        
//         // Validate date range
//         if (campaign.getEndDate().isBefore(campaign.getStartDate())) {
//             throw new IllegalArgumentException("End date must not be earlier than start date");
//         }
        
//         // Validate budget
//         if (campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
//             throw new IllegalArgumentException("Budget must be >= 0");
//         }
        
//         // Ensure active is true by default
//         if (campaign.getActive() == null) {
//             campaign.setActive(true);
//         }
        
//         return campaignRepository.save(campaign);
//     }

//     @Override
//     public Campaign updateCampaign(Long id, Campaign campaign) {
//         Campaign existingCampaign = campaignRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
        
//         // Check if campaign name is being changed to an existing one
//         if (campaign.getCampaignName() != null && 
//             !campaign.getCampaignName().equals(existingCampaign.getCampaignName()) &&
//             campaignRepository.existsByCampaignName(campaign.getCampaignName())) {
//             throw new RuntimeException("Duplicate campaign name");
//         }
        
//         // Validate date range if dates are being updated
//         if (campaign.getStartDate() != null && campaign.getEndDate() != null) {
//             if (campaign.getEndDate().isBefore(campaign.getStartDate())) {
//                 throw new IllegalArgumentException("End date must not be earlier than start date");
//             }
//         }
        
//         // Update fields if provided
//         if (campaign.getCampaignName() != null) {
//             existingCampaign.setCampaignName(campaign.getCampaignName());
//         }
//         if (campaign.getStartDate() != null) {
//             existingCampaign.setStartDate(campaign.getStartDate());
//         }
//         if (campaign.getEndDate() != null) {
//             existingCampaign.setEndDate(campaign.getEndDate());
//         }
//         if (campaign.getBudget() != null) {
//             if (campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
//                 throw new IllegalArgumentException("Budget must be >= 0");
//             }
//             existingCampaign.setBudget(campaign.getBudget());
//         }
//         if (campaign.getActive() != null) {
//             existingCampaign.setActive(campaign.getActive());
//         }
        
//         return campaignRepository.save(existingCampaign);
//     }

//     @Override
//     public Campaign getCampaignById(Long id) {
//         return campaignRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
//     }

//     @Override
//     public List<Campaign> getAllCampaigns() {
//         return campaignRepository.findAll();
//     }

//     @Override
//     public Campaign deactivateCampaign(Long id) {
//         Campaign campaign = getCampaignById(id);
//         campaign.setActive(false);
//         return campaignRepository.save(campaign);
//     }
// }