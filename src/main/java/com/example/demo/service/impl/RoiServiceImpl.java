package com.example.demo.service.impl;

import com.example.demo.model.RoiReport;
import com.example.demo.model.DiscountCode;
import com.example.demo.model.SaleTransaction;
import com.example.demo.model.Campaign;
import com.example.demo.model.Influencer;
import com.example.demo.repository.RoiReportRepository;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.service.RoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class RoiServiceImpl implements RoiService {

    private final RoiReportRepository roiReportRepository;
    private final DiscountCodeRepository discountCodeRepository;
    private final SaleTransactionRepository saleTransactionRepository;

    @Autowired
    public RoiServiceImpl(RoiReportRepository roiReportRepository,
                          DiscountCodeRepository discountCodeRepository,
                          SaleTransactionRepository saleTransactionRepository) {
        this.roiReportRepository = roiReportRepository;
        this.discountCodeRepository = discountCodeRepository;
        this.saleTransactionRepository = saleTransactionRepository;
    }

    @Override
    public RoiReport generateRoiForCode(Long codeId) {
        DiscountCode discountCode = discountCodeRepository.findById(codeId)
                .orElseThrow(() -> new RuntimeException("Discount code not found"));

        List<SaleTransaction> sales = saleTransactionRepository.findByDiscountCodeId(codeId);
        
        BigDecimal totalSales = BigDecimal.ZERO;
        BigDecimal totalRevenue = BigDecimal.ZERO;
        
        for (SaleTransaction sale : sales) {
            totalSales = totalSales.add(sale.getSaleAmount());
            // Revenue is sale amount minus discount
            BigDecimal discountAmount = sale.getSaleAmount()
                    .multiply(BigDecimal.valueOf(discountCode.getDiscountPercentage() / 100.0));
            totalRevenue = totalRevenue.add(sale.getSaleAmount().subtract(discountAmount));
        }
        
        // Calculate ROI: (Revenue - Cost) / Cost * 100
        Campaign campaign = discountCode.getCampaign();
        BigDecimal campaignBudget = campaign.getBudget();
        
        // Calculate pro-rated budget for this specific discount code
        // This is a simplification - you might want a more sophisticated allocation
        long totalCodesForCampaign = discountCodeRepository.countByCampaignId(campaign.getId());
        BigDecimal allocatedBudget = campaignBudget.divide(
            BigDecimal.valueOf(Math.max(totalCodesForCampaign, 1)), 
            2, 
            RoundingMode.HALF_UP
        );
        
        BigDecimal roiPercentage = BigDecimal.ZERO;
        if (allocatedBudget.compareTo(BigDecimal.ZERO) > 0) {
            roiPercentage = totalRevenue.subtract(allocatedBudget)
                    .divide(allocatedBudget, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }
        
        RoiReport roiReport = new RoiReport();
        roiReport.setCampaign(campaign);
        roiReport.setInfluencer(discountCode.getInfluencer());
        roiReport.setTotalSales(totalSales);
        roiReport.setTotalRevenue(totalRevenue);
        roiReport.setRoiPercentage(roiPercentage);
        
        return roiReportRepository.save(roiReport);
    }

    @Override
    public RoiReport getReportById(Long id) {
        return roiReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ROI report not found"));
    }

    @Override
    public List<RoiReport> getReportsForInfluencer(Long influencerId) {
        return roiReportRepository.findByInfluencerId(influencerId);
    }

    @Override
    public List<RoiReport> getReportsForCampaign(Long campaignId) {
        return roiReportRepository.findByCampaignId(campaignId);
    }
}