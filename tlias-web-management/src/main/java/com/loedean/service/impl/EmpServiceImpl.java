package com.loedean.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.loedean.mapper.EmpExprMapper;
import com.loedean.mapper.EmpMapper;
import com.loedean.pojo.Emp;
import com.loedean.pojo.EmpExpr;
import com.loedean.pojo.EmpQueryParam;
import com.loedean.pojo.PageResult;
import com.loedean.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper EmpExprMapper;
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
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
////        PageHelper.startPage(page, pageSize);
////        List<Emp> empList = empMapper.list();
////        Page<Emp> p = (Page<Emp>) empList;
////        return new PageResult<>(p.getTotal(), p.getResult());
//        // 推荐写法
//        try (Page<Emp> p = PageHelper.startPage(page, pageSize)) {
//            List<Emp> empList = empMapper.list();
//            return new PageResult<>(p.getTotal(), p.getResult());
//        }
//        // 自动调用 p.close() 清理资源
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        try (Page<Emp> p = PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize())) {
            List<Emp> empList = empMapper.list(empQueryParam);
            return new PageResult<>(p.getTotal(), p.getResult());
        }
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        Integer empid = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(empid);
            }
            EmpExprMapper.insertBatch(exprList);
        }
        else System.out.println("exprlist是空的");

    }
}
