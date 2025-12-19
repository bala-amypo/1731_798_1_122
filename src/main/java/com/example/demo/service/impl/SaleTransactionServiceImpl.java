package com.example.demo.service.impl;

import com.example.demo.model.SaleTransaction;
import com.example.demo.model.DiscountCode;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.service.SaleTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class SaleTransactionServiceImpl implements SaleTransactionService {

    private final SaleTransactionRepository saleTransactionRepository;
    private final DiscountCodeRepository discountCodeRepository;

    @Autowired
    public SaleTransactionServiceImpl(SaleTransactionRepository saleTransactionRepository,
                                      DiscountCodeRepository discountCodeRepository) {
        this.saleTransactionRepository = saleTransactionRepository;
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public SaleTransaction logTransaction(SaleTransaction transaction) {
        DiscountCode discountCode = discountCodeRepository.findById(transaction.getDiscountCode().getId())
                .orElseThrow(() -> new RuntimeException("Discount code not found"));

        if (!discountCode.isActive()) {
            throw new RuntimeException("Discount code is not active");
        }

        if (!discountCode.getInfluencer().isActive()) {
            throw new RuntimeException("Influencer is not active");
        }

        if (!discountCode.getCampaign().isActive()) {
            throw new RuntimeException("Campaign is not active");
        }

        if (transaction.getSaleAmount() == null || transaction.getSaleAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Sale amount must be positive");
        }

        transaction.setDiscountCode(discountCode);
        return saleTransactionRepository.save(transaction);
    }

    @Override
    public SaleTransaction getTransactionById(Long id) {
        return saleTransactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale transaction not found"));
    }

    @Override
    public List<SaleTransaction> getSalesForCode(Long codeId) {
        return saleTransactionRepository.findByDiscountCodeId(codeId);
    }

    @Override
    public List<SaleTransaction> getSalesForInfluencer(Long influencerId) {
        return saleTransactionRepository.findByDiscountCodeInfluencerId(influencerId);
    }

    @Override
    public List<SaleTransaction> getSalesForCampaign(Long campaignId) {
        return saleTransactionRepository.findByDiscountCodeCampaignId(campaignId);
    }
}