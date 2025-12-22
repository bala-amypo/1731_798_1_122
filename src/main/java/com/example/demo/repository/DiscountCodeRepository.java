// DiscountCodeRepository.java
package com.example.demo.repository;
import com.example.demo.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    @Query("SELECT d FROM DiscountCode d WHERE d.influencer.id = :influencerId")
    List<DiscountCode> findByInfluencerId(@Param("influencerId") Long influencerId);
    @Query("SELECT d FROM DiscountCode d WHERE d.campaign.id = :campaignId")
    List<DiscountCode> findByCampaignId(@Param("campaignId") Long campaignId);
}