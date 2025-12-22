// RoiReportRepository.java
package com.example.demo.repository;

import com.example.demo.model.RoiReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RoiReportRepository extends JpaRepository<RoiReport, Long> {
    @Query("SELECT r FROM RoiReport r WHERE r.discountCode.influencer.id = :influencerId")
    List<RoiReport> findByDiscountCodeInfluencerId(@Param("influencerId") Long influencerId);
    
    @Query("SELECT r FROM RoiReport r WHERE r.discountCode.id = :discountCodeId")
    RoiReport findByDiscountCodeId(@Param("discountCodeId") Long discountCodeId);
}