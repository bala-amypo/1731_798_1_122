package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Campaign;
import com.example.demo.service.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@Tag(name = "Campaigns", description = "Campaign management endpoints")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    @Operation(summary = "Create a new campaign")
    public ApiResponse<Campaign> createCampaign(@Valid @RequestBody Campaign campaign) {
        Campaign createdCampaign = campaignService.createCampaign(campaign);
        return ApiResponse.success("Campaign created successfully", createdCampaign);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing campaign")
    public ApiResponse<Campaign> updateCampaign(@PathVariable Long id, @Valid @RequestBody Campaign campaign) {
        Campaign updatedCampaign = campaignService.updateCampaign(id, campaign);
        return ApiResponse.success("Campaign updated successfully", updatedCampaign);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get campaign by ID")
    public ApiResponse<Campaign> getCampaign(@PathVariable Long id) {
        Campaign campaign = campaignService.getCampaignById(id);
        return ApiResponse.success(campaign);
    }

    @GetMapping
    @Operation(summary = "Get all campaigns")
    public ApiResponse<List<Campaign>> getAllCampaigns() {
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        return ApiResponse.success(campaigns);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a campaign")
    public ApiResponse<Campaign> deactivateCampaign(@PathVariable Long id) {
        Campaign deactivatedCampaign = campaignService.deactivateCampaign(id);
        return ApiResponse.success("Campaign deactivated successfully", deactivatedCampaign);
    }
}