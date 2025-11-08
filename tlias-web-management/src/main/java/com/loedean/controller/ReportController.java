package com.loedean.controller;

import com.loedean.pojo.JobOption;
import com.loedean.pojo.Result;
import com.loedean.service.JobOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private JobOptionService jobOptionService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各职位人数");
        JobOption jobOption = jobOptionService.getEmpJobData();
        return Result.success(jobOption);
    }
}
