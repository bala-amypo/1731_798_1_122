package com.example.demo.repository;

import com.example.demo.model.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Long> {
    List<SaleTransaction> findByDiscountCodeId(Long discountCodeId);
    List<SaleTransaction> findByDiscountCodeInfluencerId(Long influencerId);
    List<SaleTransaction> findByDiscountCodeCampaignId(Long campaignId);
}