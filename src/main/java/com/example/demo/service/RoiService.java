package com.example.demo.service;

import com.example.demo.model.RoiReport;

public interface RoiService {

    RoiReport generateRoiForCode(Long codeId);

    RoiReport getReportById(Long id);
}
