package com.example.demo.repository;

import com.example.demo.model.RoiReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoiReportRepository extends JpaRepository<RoiReport, Long> {
    List<RoiReport> findByInfluencerId(Long influencerId);
    List<RoiReport> findByCampaignId(Long campaignId);
}