package com.example.demo.repository;

import com.example.demo.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Optional<Campaign> findByCampaignName(String campaignName);
    boolean existsByCampaignName(String campaignName);
}