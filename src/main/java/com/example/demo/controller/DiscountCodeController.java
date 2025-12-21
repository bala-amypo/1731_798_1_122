package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.DiscountCode;
import com.example.demo.service.DiscountCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount-codes")
@Tag(name = "Discount Codes", description = "Discount code management endpoints")
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @PostMapping
    @Operation(summary = "Create a new discount code")
    public ApiResponse<DiscountCode> createDiscountCode(@Valid @RequestBody DiscountCode discountCode) {
        DiscountCode createdCode = discountCodeService.createDiscountCode(discountCode);
        return ApiResponse.success("Discount code created successfully", createdCode);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing discount code")
    public ApiResponse<DiscountCode> updateDiscountCode(@PathVariable Long id, @Valid @RequestBody DiscountCode discountCode) {
        DiscountCode updatedCode = discountCodeService.updateDiscountCode(id, discountCode);
        return ApiResponse.success("Discount code updated successfully", updatedCode);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get discount code by ID")
    public ApiResponse<DiscountCode> getDiscountCode(@PathVariable Long id) {
        DiscountCode discountCode = discountCodeService.getCodeById(id);
        return ApiResponse.success(discountCode);
    }

    @GetMapping("/influencer/{influencerId}")
    @Operation(summary = "Get discount codes by influencer")
    public ApiResponse<List<DiscountCode>> getCodesByInfluencer(@PathVariable Long influencerId) {
        List<DiscountCode> codes = discountCodeService.getCodesByInfluencer(influencerId);
        return ApiResponse.success(codes);
    }

    @GetMapping("/campaign/{campaignId}")
    @Operation(summary = "Get discount codes by campaign")
    public ApiResponse<List<DiscountCode>> getCodesByCampaign(@PathVariable Long campaignId) {
        List<DiscountCode> codes = discountCodeService.getCodesByCampaign(campaignId);
        return ApiResponse.success(codes);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a discount code")
    public ApiResponse<DiscountCode> deactivateCode(@PathVariable Long id) {
        DiscountCode deactivatedCode = discountCodeService.deactivateCode(id);
        return ApiResponse.success("Discount code deactivated successfully", deactivatedCode);
    }
}