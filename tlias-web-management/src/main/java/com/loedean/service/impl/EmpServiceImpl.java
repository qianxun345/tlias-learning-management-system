package com.loedean.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.loedean.mapper.EmpMapper;
import com.loedean.pojo.Emp;
import com.loedean.pojo.PageResult;
import com.loedean.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long total = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        return new PageResult<>(total, rows);
//
//    }

     /*
     使用PageHelper
     注意事项：
     1. sql语句结尾不能加分号
     2. 紧跟着PageHelper.startPage()的第一条sql语句才生效
     */
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        PageHelper.startPage(page, pageSize);
//        List<Emp> empList = empMapper.list();
//        Page<Emp> p = (Page<Emp>) empList;
//        return new PageResult<>(p.getTotal(), p.getResult());
        // 推荐写法
        try (Page<Emp> p = PageHelper.startPage(page, pageSize)) {
            List<Emp> empList = empMapper.list();
            return new PageResult<>(p.getTotal(), p.getResult());
        }
        // 自动调用 p.close() 清理资源
    }
}
