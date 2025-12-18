package com.example.demo.service.impl;

import com.example.demo.model.RoiReport;
import com.example.demo.repository.RoiReportRepository;
import com.example.demo.repository.SaleTransactionRepository;
import com.example.demo.service.RoiService;

import java.math.BigDecimal;

public class RoiServiceImpl implements RoiService {

    private final RoiReportRepository roiReportRepository;
    private final SaleTransactionRepository saleTransactionRepository;

    // 🚨 EXACT constructor order
    public RoiServiceImpl(
            RoiReportRepository roiReportRepository,
            SaleTransactionRepository saleTransactionRepository
    ) {
        this.roiReportRepository = roiReportRepository;
        this.saleTransactionRepository = saleTransactionRepository;
    }

    @Override
    public RoiReport generateRoiForCode(Long codeId) {
        BigDecimal totalSales = saleTransactionRepository
                .findByDiscountCode_Id(codeId)
                .stream()
                .map(t -> t.getSaleAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        RoiReport report = new RoiReport();
        report.setTotalSales(totalSales);
        report.setTotalRevenue(totalSales);
        report.setRoiPercentage(BigDecimal.ZERO);

        return roiReportRepository.save(report);
    }

    @Override
    public RoiReport getReportById(Long id) {
        return roiReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ROI report not found"));
    }
}
