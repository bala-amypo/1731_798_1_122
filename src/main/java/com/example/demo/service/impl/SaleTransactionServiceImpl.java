package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SaleTransaction;
import com.example.demo.model.DiscountCode;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.service.SaleTransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleTransactionServiceImpl implements SaleTransactionService {

    private final SaleTransactionRepository saleTransactionRepository;
    private final DiscountCodeRepository discountCodeRepository;

    public SaleTransactionServiceImpl(SaleTransactionRepository saleTransactionRepository,
                                     DiscountCodeRepository discountCodeRepository) {
        this.saleTransactionRepository = saleTransactionRepository;
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public SaleTransaction logTransaction(SaleTransaction transaction) {
        // Validate sale amount
        if (transaction.getSaleAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Sale amount must be > 0");
        }
        
        // Verify discount code exists and is active
        DiscountCode discountCode = discountCodeRepository.findById(transaction.getDiscountCode().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
        
        if (!discountCode.getActive()) {
            throw new IllegalArgumentException("Discount code is not active");
        }
        
        // Set the actual discount code entity
        transaction.setDiscountCode(discountCode);
        
        return saleTransactionRepository.save(transaction);
    }

    @Override
    public SaleTransaction getTransactionById(Long id) {
        return saleTransactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale transaction not found"));
    }

    @Override
    public List<SaleTransaction> getSalesForCode(Long codeId) {
        // Verify discount code exists
        discountCodeRepository.findById(codeId)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
        
        return saleTransactionRepository.findByDiscountCodeId(codeId);
    }

    @Override
    public List<SaleTransaction> getSalesForInfluencer(Long influencerId) {
        // Note: No need to verify influencer exists here as the repository method will handle it
        return saleTransactionRepository.findByDiscountCode_Influencer_Id(influencerId);
    }

    @Override
    public List<SaleTransaction> getSalesForCampaign(Long campaignId) {
        // Note: No need to verify campaign exists here as the repository method will handle it
        return saleTransactionRepository.findByDiscountCode_Campaign_Id(campaignId);
    }
}