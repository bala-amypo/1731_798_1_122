package com.example.demo.repository;

import com.example.demo.model.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Long> {
    List<SaleTransaction> findByDiscountCodeId(Long discountCodeId);
    List<SaleTransaction> findByDiscountCode_Influencer_Id(Long influencerId);
    List<SaleTransaction> findByDiscountCode_Campaign_Id(Long campaignId);
}