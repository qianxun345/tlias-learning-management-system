package com.loedean.service;

import com.loedean.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface JobOptionService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();
}
