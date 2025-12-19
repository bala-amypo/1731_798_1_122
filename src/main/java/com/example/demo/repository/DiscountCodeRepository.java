package com.example.demo.repository;

import com.example.demo.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    boolean existsByCode(String code);
    // Required signatures for cross-referencing
    List<DiscountCode> findByInfluencer_Id(Long influencerId);
    List<DiscountCode> findByCampaign_Id(Long campaignId);
}