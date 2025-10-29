package com.loedean.controller;

import com.loedean.pojo.Emp;
import com.loedean.pojo.EmpQueryParam;
import com.loedean.pojo.PageResult;
import com.loedean.pojo.Result;
import com.loedean.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
//    @GetMapping
//    public Result info(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("查询员工，page={}，pageSize={}", page, pageSize);
//        PageResult<Emp> pageResult = empService.page(page, pageSize);
//        return Result.success(pageResult);
//    }

    @GetMapping
    public Result page(EmpQueryParam  empQueryParam){
        log.info("查询员工，{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info("添加员工，{}", emp);
        empService.save(emp);
        return Result.success();
    }
}
