package com.loedean.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.loedean.mapper.EmpExprMapper;
import com.loedean.mapper.EmpMapper;
import com.loedean.pojo.*;
import com.loedean.service.EmpLogService;
import com.loedean.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper EmpExprMapper;

    @Autowired
    private EmpLogService EmpLogService;
    @Autowired
    private EmpExprMapper empExprMapper;
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

    //默认情况，只有RuntimeException 才会回滚
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        try {
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
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            EmpLogService.insertLog(empLog);
        }

    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        EmpExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1. 更新员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 删除老的工作经历信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //3. 新增新的工作经历信息
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(empId);
            }
            EmpExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {

        Emp loginEmp = empMapper.getByUsernameAndPassword(emp);
        if(loginEmp != null){
            return new LoginInfo(loginEmp.getId(), loginEmp.getUsername(), loginEmp.getName(), "");
        }
        return null;
    }
}
