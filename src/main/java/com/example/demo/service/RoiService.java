// RoiService.java
package com.example.demo.service;

import com.example.demo.model.RoiReport;
import com.example.demo.repository.RoiReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoiService {
    @Autowired
    private RoiReportRepository roiReportRepository;
    
    public RoiReport calculateAndSaveRoi(Long discountCodeId) {
        // Calculate ROI logic here
        RoiReport report = new RoiReport();
        // ... calculation logic
        return roiReportRepository.save(report);
    }
    
    public List<RoiReport> getReportsForInfluencer(Long influencerId) {
        return roiReportRepository.findByDiscountCodeInfluencerId(influencerId);
    }
    
    public RoiReport getReportForDiscountCode(Long discountCodeId) {
        return roiReportRepository.findByDiscountCodeId(discountCodeId);
    }
}