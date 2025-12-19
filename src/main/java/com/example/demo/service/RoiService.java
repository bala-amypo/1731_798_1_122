package com.example.demo.service;

import com.example.demo.model.RoiReport;
import java.util.List;

public interface RoiService {
    RoiReport generateReportForCode(Long discountCodeId); // MUST be this name
    RoiReport getReportById(Long id);
    List<RoiReport> getReportsForInfluencer(Long influencerId);
    List<RoiReport> getReportsForCampaign(Long campaignId);
}