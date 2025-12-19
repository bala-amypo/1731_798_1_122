package com.example.demo.service;

import com.example.demo.model.SaleTransaction;
import java.util.List;

public interface SaleTransactionService {
    // Change createSale to logTransaction to match implementation
    SaleTransaction logTransaction(SaleTransaction transaction); 
    SaleTransaction getTransactionById(Long id);
    List<SaleTransaction> getSalesForCode(Long discountCodeId);
    List<SaleTransaction> getSalesForInfluencer(Long influencerId);
    List<SaleTransaction> getSalesForCampaign(Long campaignId);
}