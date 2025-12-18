package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RoiService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RoiServiceImpl implements RoiService {
    private final RoiReportRepository roiRepo;
    private final SaleTransactionRepository saleRepo;
    private final DiscountCodeRepository codeRepo;

    public RoiServiceImpl(RoiReportRepository roiRepo, SaleTransactionRepository saleRepo, DiscountCodeRepository codeRepo) {
        this.roiRepo = roiRepo;
        this.saleRepo = saleRepo;
        this.codeRepo = codeRepo;
    }

    @Override
    public RoiReport generateRoiForCode(Long codeId) {
        DiscountCode code = codeRepo.findById(codeId)
                .orElseThrow(() -> new RuntimeException("Discount code not found"));

        List<SaleTransaction> transactions = saleRepo.findByDiscountCode_Id(codeId);
        BigDecimal totalRevenue = transactions.stream()
                .map(SaleTransaction::getSaleAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        RoiReport report = new RoiReport();
        report.setCampaign(code.getCampaign());
        report.setInfluencer(code.getInfluencer());
        report.setTotalSales(BigDecimal.valueOf(transactions.size()));
        report.setTotalRevenue(totalRevenue);

        BigDecimal budget = code.getCampaign().getBudget();
        if (budget.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal roi = totalRevenue.divide(budget, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            report.setRoiPercentage(roi);
        } else {
            report.setRoiPercentage(BigDecimal.ZERO);
        }
        return roiRepo.save(report);
    }

    @Override
    public RoiReport getReportById(Long id) {
        return roiRepo.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Override
    public List<RoiReport> getReportsForInfluencer(Long influencerId) {
        return roiRepo.findByInfluencer_Id(influencerId);
    }

    @Override
    public List<RoiReport> getReportsForCampaign(Long campaignId) {
        return roiRepo.findByCampaign_Id(campaignId);
    }
}