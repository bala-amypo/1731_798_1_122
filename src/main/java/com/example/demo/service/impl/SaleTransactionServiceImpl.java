package com.example.demo.service.impl;

import com.example.demo.model.DiscountCode;
import com.example.demo.model.SaleTransaction;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.service.SaleTransactionService;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

public class SaleTransactionServiceImpl implements SaleTransactionService {

    private final SaleTransactionRepository saleTransactionRepository;
    private final DiscountCodeRepository discountCodeRepository;

    public SaleTransactionServiceImpl(
            SaleTransactionRepository saleTransactionRepository,
            DiscountCodeRepository discountCodeRepository
    ) {
        this.saleTransactionRepository = saleTransactionRepository;
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public SaleTransaction logTransaction(SaleTransaction transaction) {
        DiscountCode code = discountCodeRepository.findById(
                transaction.getDiscountCode().getId()
        ).orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));

        if (!Boolean.TRUE.equals(code.getActive())) {
            throw new IllegalStateException("Discount code inactive");
        }

        transaction.setDiscountCode(code);
        return saleTransactionRepository.save(transaction);
    }

    @Override
    public SaleTransaction getTransactionById(Long id) {
        return saleTransactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }

    @Override
    public List<SaleTransaction> getSalesForCode(Long codeId) {
        return saleTransactionRepository.findByDiscountCode_Id(codeId);
    }

    @Override
    public List<SaleTransaction> getSalesForInfluencer(Long influencerId) {
        return saleTransactionRepository.findByDiscountCode_Influencer_Id(influencerId);
    }

    @Override
    public List<SaleTransaction> getSalesForCampaign(Long campaignId) {
        return saleTransactionRepository.findByDiscountCode_Campaign_Id(campaignId);
    }
}
