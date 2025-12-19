package com.example.demo.controller;

import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@Tag(name = "Sales Transactions")
public class SaleTransactionController {

    private final SaleTransactionService saleTransactionService;

    // Constructor-based dependency injection
    public SaleTransactionController(SaleTransactionService saleTransactionService) {
        this.saleTransactionService = saleTransactionService;
    }

    /**
     * Requirement 7.5: POST /sales
     * Behaviour: call createSale(transaction) and return created entity.
     */
    @PostMapping
    public ResponseEntity<SaleTransaction> createSale(@RequestBody SaleTransaction transaction) {
        return ResponseEntity.ok(saleTransactionService.logTransaction(transaction));
    }

    /**
     * Requirement 7.5: GET /sales/code/{discountCodeId}
     * Behaviour: return list from getSalesForCode(discountCodeId).
     */
    @GetMapping("/code/{discountCodeId}")
    public ResponseEntity<List<SaleTransaction>> getByCode(@PathVariable Long discountCodeId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForCode(discountCodeId));
    }

    /**
     * Requirement 7.5: GET /sales/influencer/{influencerId}
     * Behaviour: return list from getSalesForInfluencer(influencerId).
     */
    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<SaleTransaction>> getByInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForInfluencer(influencerId));
    }

    /**
     * Requirement 7.5: GET /sales/campaign/{campaignId}
     * Behaviour: return list from getSalesForCampaign(campaignId).
     */
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<SaleTransaction>> getByCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForCampaign(campaignId));
    }
}