package com.example.demo.repository;

import com.example.demo.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    boolean existsByCode(String code);
    List<DiscountCode> findByInfluencerId(Long influencerId);
    List<DiscountCode> findByCampaignId(Long campaignId);
    long countByCampaignId(Long campaignId);
}