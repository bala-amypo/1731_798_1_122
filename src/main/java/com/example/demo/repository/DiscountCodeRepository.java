package com.example.demo.repository;

import com.example.demo.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    Optional<DiscountCode> findByCode(String code);
    boolean existsByCode(String code);  // Added this method
    List<DiscountCode> findByInfluencerId(Long influencerId);
    List<DiscountCode> findByCampaignId(Long campaignId);
}