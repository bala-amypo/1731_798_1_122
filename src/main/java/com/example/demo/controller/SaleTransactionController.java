// SaleTransactionController.java
package com.example.demo.controller;

import com.example.demo.model.SaleTransaction;
import com.example.demo.service.SaleTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleTransactionController {
    @Autowired
    private SaleTransactionService saleTransactionService;
    
    @PostMapping
    public ResponseEntity<SaleTransaction> createSale(@RequestBody SaleTransaction saleTransaction) {
        return ResponseEntity.ok(saleTransactionService.createSale(saleTransaction));
    }
    
    @GetMapping("/discount-code/{discountCodeId}")
    public ResponseEntity<List<SaleTransaction>> getSalesForCode(@PathVariable Long discountCodeId) {
        return ResponseEntity.ok(saleTransactionService.getSalesForCode(discountCodeId));
    }
}