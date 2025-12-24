// SaleTransactionService.java
package com.example.demo.service;

import com.example.demo.model.SaleTransaction;
import com.example.demo.repository.SaleTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal; 


@Service
public class SaleTransactionService {
    @Autowired
    private SaleTransactionRepository saleTransactionRepository;
    
    // public SaleTransaction createSale(SaleTransaction saleTransaction) {
    //     return saleTransactionRepository.save(saleTransaction);
    // }
    public SaleTransaction createSale(SaleTransaction saleTransaction) {
    // Validate amount here
    if (saleTransaction.getTransactionAmount().compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException("Transaction amount must be > 0");
    }
    return saleTransactionRepository.save(saleTransaction);
}
    public List<SaleTransaction> getSalesForCode(Long discountCodeId) {
        return saleTransactionRepository.findByDiscountCodeId(discountCodeId);
    }
    
    public List<SaleTransaction> getSalesForInfluencer(Long influencerId) {
        return saleTransactionRepository.findSalesByInfluencerId(influencerId);
    }
    
    public List<SaleTransaction> getSalesForCampaign(Long campaignId) {
        return saleTransactionRepository.findSalesByCampaignId(campaignId);
    }
}