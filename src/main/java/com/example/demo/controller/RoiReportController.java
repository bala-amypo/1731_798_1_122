package com.example.demo.controller;

import com.example.demo.model.RoiReport;
import com.example.demo.service.RoiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roi")
@Tag(name = "ROI Reports")
public class RoiReportController {

    private final RoiService roiService;

    public RoiReportController(RoiService roiService) {
        this.roiService = roiService;
    }

    @PostMapping("/generate/{codeId}")
    public RoiReport generate(@PathVariable Long codeId) {
        return roiService.generateRoiForCode(codeId);
    }

    @GetMapping("/{id}")
    public RoiReport getById(@PathVariable Long id) {
        return roiService.getReportById(id);
    }
}
