package com.example.demo.service.impl;

import com.example.demo.model.DiscountCode;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.InfluencerRepository;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.service.DiscountCodeService;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

public class DiscountCodeServiceImpl implements DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;
    private final InfluencerRepository influencerRepository;
    private final SaleTransactionRepository saleTransactionRepository;

    // ✅ EXACT constructor order required by tests
    public DiscountCodeServiceImpl(
            DiscountCodeRepository discountCodeRepository,
            InfluencerRepository influencerRepository,
            SaleTransactionRepository saleTransactionRepository
    ) {
        this.discountCodeRepository = discountCodeRepository;
        this.influencerRepository = influencerRepository;
        this.saleTransactionRepository = saleTransactionRepository;
    }

    @Override
    public DiscountCode createDiscountCode(DiscountCode code) {
        return discountCodeRepository.save(code);
    }

    @Override
    public DiscountCode updateDiscountCode(Long id, DiscountCode code) {
        DiscountCode existing = discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
        existing.setCode(code.getCode());
        existing.setDiscountPercentage(code.getDiscountPercentage());
        existing.setActive(code.getActive());
        return discountCodeRepository.save(existing);
    }

    @Override
    public DiscountCode getCodeById(Long id) {
        return discountCodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));
    }

    @Override
    public List<DiscountCode> getCodesByInfluencer(Long influencerId) {
        return discountCodeRepository.findByInfluencer_Id(influencerId);
    }

    @Override
    public List<DiscountCode> getCodesByCampaign(Long campaignId) {
        return discountCodeRepository.findByCampaign_Id(campaignId);
    }

    @Override
    public void deactivateCode(Long id) {
        DiscountCode code = getCodeById(id);
        code.setActive(false);
        discountCodeRepository.save(code);
    }
}
