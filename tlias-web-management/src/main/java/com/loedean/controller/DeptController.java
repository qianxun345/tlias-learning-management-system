package com.loedean.controller;

import com.loedean.pojo.Dept;
import com.loedean.pojo.Result;
import com.loedean.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
        System.out.println("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
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

    @DeleteMapping("/depts")
    public Result delete(Integer id){
        System.out.println("删除部门:" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        System.out.println("添加部门:" + dept);
        deptService.save(dept);

        return Result.success();
    }
}
