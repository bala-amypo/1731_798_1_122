// SaleTransactionRepository.java
package com.example.demo.repository;
import com.example.demo.model.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Long> {
    List<SaleTransaction> findByDiscountCodeId(Long discountCodeId);
    @Query("SELECT s FROM SaleTransaction s WHERE s.discountCode.influencer.id = :influencerId")
    List<SaleTransaction> findSalesByInfluencerId(@Param("influencerId") Long influencerId);
    @Query("SELECT s FROM SaleTransaction s WHERE s.discountCode.campaign.id = :campaignId")
    List<SaleTransaction> findSalesByCampaignId(@Param("campaignId") Long campaignId);
}