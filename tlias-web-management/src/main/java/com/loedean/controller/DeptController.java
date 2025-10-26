package com.loedean.controller;

import com.loedean.pojo.Dept;
import com.loedean.pojo.Result;
import com.loedean.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list(){
//        System.out.println("查询全部的部门数据");
        log.info("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer id){
//        System.out.println("查询部门id:" + id);
        log.info("查询部门id:{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    // 方法一：HttpServletRequest方式
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("删除部门:" + id);
//        return Result.success();
//    }

    // 方法二：@RequestParam方式，RequestParam的参数必须传递
    // 如果形参的名称和请求参数的名称一致，可以省略@RequestParam
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id") Integer id){
//        System.out.println("删除部门:" + id);
//        return Result.success();
//    }

    @DeleteMapping
    public Result delete(Integer id){
//        System.out.println("删除部门:" + id);
        log.info("删除部门:{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
//        System.out.println("添加部门:" + dept);
        log.info("添加部门:{}", dept);
        deptService.save(dept);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门:" + dept);
        log.info("修改部门:{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
