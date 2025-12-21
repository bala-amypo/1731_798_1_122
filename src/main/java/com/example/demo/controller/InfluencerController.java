package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Influencer;
import com.example.demo.service.InfluencerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/influencers")
@Tag(name = "Influencers", description = "Influencer management endpoints")
public class InfluencerController {

    private final InfluencerService influencerService;

    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    @PostMapping
    @Operation(summary = "Create a new influencer")
    public ApiResponse<Influencer> createInfluencer(@Valid @RequestBody Influencer influencer) {
        Influencer createdInfluencer = influencerService.createInfluencer(influencer);
        return ApiResponse.success("Influencer created successfully", createdInfluencer);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing influencer")
    public ApiResponse<Influencer> updateInfluencer(@PathVariable Long id, @Valid @RequestBody Influencer influencer) {
        Influencer updatedInfluencer = influencerService.updateInfluencer(id, influencer);
        return ApiResponse.success("Influencer updated successfully", updatedInfluencer);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get influencer by ID")
    public ApiResponse<Influencer> getInfluencer(@PathVariable Long id) {
        Influencer influencer = influencerService.getInfluencerById(id);
        return ApiResponse.success(influencer);
    }

    @GetMapping
    @Operation(summary = "Get all influencers")
    public ApiResponse<List<Influencer>> getAllInfluencers() {
        List<Influencer> influencers = influencerService.getAllInfluencers();
        return ApiResponse.success(influencers);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate an influencer")
    public ApiResponse<Influencer> deactivateInfluencer(@PathVariable Long id) {
        Influencer deactivatedInfluencer = influencerService.deactivateInfluencer(id);
        return ApiResponse.success("Influencer deactivated successfully", deactivatedInfluencer);
    }
}