// RoiReportController.java
package com.example.demo.controller;

import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roi-reports")
public class RoiReportController {
    @Autowired
    private RoiService roiService;
    
    @GetMapping("/discount-code/{discountCodeId}")
    public ResponseEntity<RoiReport> getReportForDiscountCode(@PathVariable Long discountCodeId) {
        return ResponseEntity.ok(roiService.getReportForDiscountCode(discountCodeId));
    }
    
    @GetMapping("/influencer/{influencerId}")
    public ResponseEntity<List<RoiReport>> getReportsForInfluencer(@PathVariable Long influencerId) {
        return ResponseEntity.ok(roiService.getReportsForInfluencer(influencerId));
    }
}