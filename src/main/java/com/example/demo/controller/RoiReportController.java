package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roi")
@Tag(name = "ROI Reports", description = "ROI report management endpoints")
public class RoiReportController {

    private final RoiService roiService;

    public RoiReportController(RoiService roiService) {
        this.roiService = roiService;
    }

    @PostMapping("/generate/{codeId}")
    @Operation(summary = "Generate ROI report for a discount code")
    public ApiResponse<RoiReport> generateRoiForCode(@PathVariable Long codeId) {
        RoiReport report = roiService.generateRoiForCode(codeId);
        return ApiResponse.success("ROI report generated successfully", report);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ROI report by ID")
    public ApiResponse<RoiReport> getReport(@PathVariable Long id) {
        RoiReport report = roiService.getReportById(id);
        return ApiResponse.success(report);
    }

    @GetMapping("/influencer/{influencerId}")
    @Operation(summary = "Get ROI reports for an influencer")
    public ApiResponse<List<RoiReport>> getReportsForInfluencer(@PathVariable Long influencerId) {
        List<RoiReport> reports = roiService.getReportsForInfluencer(influencerId);
        return ApiResponse.success(reports);
    }

    @GetMapping("/campaign/{campaignId}")
    @Operation(summary = "Get ROI reports for a campaign")
    public ApiResponse<List<RoiReport>> getReportsForCampaign(@PathVariable Long campaignId) {
        List<RoiReport> reports = roiService.getReportsForCampaign(campaignId);
        return ApiResponse.success(reports);
    }
}