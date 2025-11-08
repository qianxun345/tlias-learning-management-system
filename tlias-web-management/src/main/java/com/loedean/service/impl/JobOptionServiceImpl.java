package com.loedean.service.impl;

import com.loedean.mapper.EmpMapper;
import com.loedean.pojo.JobOption;
import com.loedean.service.JobOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobOptionServiceImpl implements JobOptionService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }
}
