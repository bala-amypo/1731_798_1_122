// SaleTransactionService.java
package com.example.demo.service;

import com.example.demo.model.SaleTransaction;
import com.example.demo.repository.SaleTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SaleTransactionService {
    @Autowired
    private SaleTransactionRepository saleTransactionRepository;
    
    public SaleTransaction createSale(SaleTransaction saleTransaction) {
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