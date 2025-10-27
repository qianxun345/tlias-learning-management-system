package com.loedean.service;

import com.loedean.pojo.Emp;
import com.loedean.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
