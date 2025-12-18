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

    @PostMapping("/")
    public ResponseEntity<SaleTransaction> log(@RequestBody SaleTransaction transaction) {
        return ResponseEntity.ok(saleTransactionService.logTransaction(transaction));
    }

    @GET("/{id}")
    public ResponseEntity<SaleTransaction> getById(@PathVariable Long id) {
        return ResponseEntity.ok(saleTransactionService.getTransactionById(id));
    }

    @GET("/code/{codeId}")
    public ResponseEntity<List<SaleTransaction>> getByCode(@PathVariable Long codeId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForCode(codeId));
    }

    @GET("/influencer/{influencerId}")
    public ResponseEntity<List<SaleTransaction>> getByInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForInfluencer(influencerId));
    }

    @GET("/campaign/{campaignId}")
    public ResponseEntity<List<SaleTransaction>> getByCampaign(@PathVariable Long campaignId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForCampaign(campaignId));
    }
}