package com.example.demo.controller;
import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales Transactions")
public class SaleTransactionController {

    private final SaleTransactionService saleTransactionService;

    public SaleTransactionController(SaleTransactionService saleTransactionService) {
        this.saleTransactionService = saleTransactionService;
    }

    @PostMapping
    public ResponseEntity<SaleTransaction> createSale(@RequestBody SaleTransaction transaction) {
        return ResponseEntity.ok(saleTransactionService.logTransaction(transaction));
    }

    @GetMapping("/code/{discountCodeId}")
    public ResponseEntity<List<SaleTransaction>> getByCode(@PathVariable Long discountCodeId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForCode(discountCodeId));
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<SaleTransaction>> getByInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForInfluencer(influencerId));
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<SaleTransaction>> getByCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForCampaign(campaignId));
    }
}
