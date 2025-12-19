package com.example.demo.controller;

import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/roi")
@Tag(name = "ROI Reports")
@CrossOrigin(origins = "*")
public class RoiReportController {

    @Autowired
    private RoiService roiService;

    @PostMapping("/generate/{codeId}")
    public ResponseEntity<RoiReport> generateRoiForCode(@PathVariable Long codeId) {
        RoiReport report = roiService.generateRoiForCode(codeId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoiReport> getReportById(@PathVariable Long id) {
        RoiReport report = roiService.getReportById(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<RoiReport>> getReportsForInfluencer(@PathVariable Long influencerId) {
        List<RoiReport> reports = roiService.getReportsForInfluencer(influencerId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<RoiReport>> getReportsForCampaign(@PathVariable Long campaignId) {
        List<RoiReport> reports = roiService.getReportsForCampaign(campaignId);
        return ResponseEntity.ok(reports);
    }
}