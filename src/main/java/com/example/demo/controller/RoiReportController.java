package com.example.demo.controller;

import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roi")
@Tag(name = "ROI Reports")
public class RoiReportController {

    private final RoiService roiService;

    public RoiReportController(RoiService roiService) {
        this.roiService = roiService;
    }

    @PostMapping("/code/{discountCodeId}")
    public ResponseEntity<RoiReport> generate(@PathVariable Long discountCodeId) {
        // FIXED: Changed generateRoiForCode to generateReportForCode
        return ResponseEntity.ok(roiService.generateReportForCode(discountCodeId));
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<RoiReport> getById(@PathVariable Long reportId) {
        return ResponseEntity.ok(roiService.getReportById(reportId));
    }

    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<RoiReport>> getByInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(roiService.getReportsForInfluencer(influencerId));
    }
}