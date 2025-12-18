package com.example.demo.controller;

import com.example.demo.model.DiscountCode;
import com.example.demo.service.DiscountCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount-codes")
@Tag(name = "Discount Codes")
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @PostMapping
    public DiscountCode create(@RequestBody DiscountCode code) {
        return discountCodeService.createDiscountCode(code);
    }

    @PutMapping("/{id}")
    public DiscountCode update(@PathVariable Long id, @RequestBody DiscountCode code) {
        return discountCodeService.updateDiscountCode(id, code);
    }

    @GetMapping("/{id}")
    public DiscountCode getById(@PathVariable Long id) {
        return discountCodeService.getCodeById(id);
    }

    @GetMapping("/influencer/{influencerId}")
    public List<DiscountCode> getByInfluencer(@PathVariable Long influencerId) {
        return discountCodeService.getCodesByInfluencer(influencerId);
    }

    @GetMapping("/campaign/{campaignId}")
    public List<DiscountCode> getByCampaign(@PathVariable Long campaignId) {
        return discountCodeService.getCodesByCampaign(campaignId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        discountCodeService.deactivateCode(id);
    }
}
