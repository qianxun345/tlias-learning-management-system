package com.loedean.controller;

import com.loedean.pojo.Emp;
import com.loedean.pojo.PageResult;
import com.loedean.pojo.Result;
import com.loedean.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
    @GetMapping
    public Result info(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查询员工，page={}，pageSize={}", page, pageSize);
        PageResult<Emp> pageResult = empService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
