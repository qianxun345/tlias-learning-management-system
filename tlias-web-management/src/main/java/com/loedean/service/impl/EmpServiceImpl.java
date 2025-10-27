package com.loedean.service.impl;

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
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        Long total = empMapper.count();
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        return new PageResult<>(total, rows);

    }
}
