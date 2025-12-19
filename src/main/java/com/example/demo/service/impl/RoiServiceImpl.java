package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DiscountCode;
import com.example.demo.model.RoiReport;
import com.example.demo.model.SaleTransaction;
import com.example.demo.repository.DiscountCodeRepository;
import com.example.demo.repository.RoiReportRepository;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.service.RoiService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoiServiceImpl implements RoiService {

    private final RoiReportRepository roiReportRepository;
    private final SaleTransactionRepository saleTransactionRepository;
    private final DiscountCodeRepository discountCodeRepository;

    public RoiServiceImpl(RoiReportRepository roiReportRepository,
                          SaleTransactionRepository saleTransactionRepository,
                          DiscountCodeRepository discountCodeRepository) {
        this.roiReportRepository = roiReportRepository;
        this.saleTransactionRepository = saleTransactionRepository;
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public RoiReport generateReportForCode(Long discountCodeId) {

        DiscountCode code = discountCodeRepository.findById(discountCodeId)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));

        List<SaleTransaction> transactions =
                saleTransactionRepository.findByDiscountCode_Id(discountCodeId);

        BigDecimal totalSales = transactions.stream()
                .map(SaleTransaction::getTransactionAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Integer totalTransactions = transactions.size();

        RoiReport report = new RoiReport();
        report.setDiscountCode(code);
        report.setTotalSales(totalSales);
        report.setTotalTransactions(totalTransactions);
        report.setRoiPercentage(10.0);

        return roiReportRepository.save(report);
    }

    @Override
    public RoiReport getReportById(Long reportId) {
        return roiReportRepository.findById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("ROI report not found"));
    }

    @Override
    public List<RoiReport> getReportsForInfluencer(Long influencerId) {
        return roiReportRepository.findByDiscountCodeInfluencerId(influencerId);
    }

    @Override
    public List<RoiReport> getReportsForCampaign(Long campaignId) {
        return roiReportRepository.findAll().stream()
                .filter(report -> report.getDiscountCode().getCampaign().getId().equals(campaignId))
                .collect(Collectors.toList());
    }
}
