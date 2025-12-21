package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales Transactions", description = "Sale transaction management endpoints")
public class SaleTransactionController {

    private final SaleTransactionService saleTransactionService;

    public SaleTransactionController(SaleTransactionService saleTransactionService) {
        this.saleTransactionService = saleTransactionService;
    }

    @PostMapping
    @Operation(summary = "Log a new sale transaction")
    public ApiResponse<SaleTransaction> logTransaction(@Valid @RequestBody SaleTransaction transaction) {
        SaleTransaction loggedTransaction = saleTransactionService.logTransaction(transaction);
        return ApiResponse.success("Sale transaction logged successfully", loggedTransaction);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get sale transaction by ID")
    public ApiResponse<SaleTransaction> getTransaction(@PathVariable Long id) {
        SaleTransaction transaction = saleTransactionService.getTransactionById(id);
        return ApiResponse.success(transaction);
    }

    @GetMapping("/code/{codeId}")
    @Operation(summary = "Get sales for a discount code")
    public ApiResponse<List<SaleTransaction>> getSalesForCode(@PathVariable Long codeId) {
        List<SaleTransaction> sales = saleTransactionService.getSalesForCode(codeId);
        return ApiResponse.success(sales);
    }

    @GetMapping("/influencer/{influencerId}")
    @Operation(summary = "Get sales for an influencer")
    public ApiResponse<List<SaleTransaction>> getSalesForInfluencer(@PathVariable Long influencerId) {
        List<SaleTransaction> sales = saleTransactionService.getSalesForInfluencer(influencerId);
        return ApiResponse.success(sales);
    }

    @GetMapping("/campaign/{campaignId}")
    @Operation(summary = "Get sales for a campaign")
    public ApiResponse<List<SaleTransaction>> getSalesForCampaign(@PathVariable Long campaignId) {
        List<SaleTransaction> sales = saleTransactionService.getSalesForCampaign(campaignId);
        return ApiResponse.success(sales);
    }
}