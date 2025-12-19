package com.example.demo.service.impl;

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
    private final SaleTransactionRepository repository;
    private final DiscountCodeRepository codeRepo;

    public SaleTransactionServiceImpl(SaleTransactionRepository repository, DiscountCodeRepository codeRepo) {
        this.repository = repository;
        this.codeRepo = codeRepo;
    }

    @Override
    public SaleTransaction logTransaction(SaleTransaction transaction) {
        DiscountCode code = codeRepo.findById(transaction.getDiscountCode().getId())
                .orElseThrow(() -> new RuntimeException("Discount code not found"));
        
        if (!code.getActive()) {
            throw new RuntimeException("Discount code is not active");
        }
        if (transaction.getSaleAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Sale amount must be positive");
        }
        return repository.save(transaction);
    }

    @Override
    public SaleTransaction getTransactionById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public List<SaleTransaction> getSalesForCode(Long codeId) {
        return repository.findByDiscountCode_Id(codeId);
    }

    @Override
    public List<SaleTransaction> getSalesForInfluencer(Long influencerId) {
        return repository.findByDiscountCode_Influencer_Id(influencerId);
    }

    @Override
    public List<SaleTransaction> getSalesForCampaign(Long campaignId) {
        return repository.findByDiscountCode_Campaign_Id(campaignId);
    }
}