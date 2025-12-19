package com.example.demo.controller;

import com.example.demo.model.DiscountCode;
import com.example.demo.service.DiscountCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    public ResponseEntity<DiscountCode> create(@RequestBody DiscountCode code) {
        return ResponseEntity.ok(discountCodeService.createDiscountCode(code));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountCode> update(@PathVariable Long id, @RequestBody DiscountCode code) {
        return ResponseEntity.ok(discountCodeService.updateDiscountCode(id, code));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountCode> getById(@PathVariable Long id) {
        return ResponseEntity.ok(discountCodeService.getCodeById(id));
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<DiscountCode>> getByInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(discountCodeService.getCodesByInfluencer(influencerId));
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<DiscountCode>> getByCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(discountCodeService.getCodesByCampaign(campaignId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        discountCodeService.deactivateCode(id);
        return ResponseEntity.ok().build();
    }
}