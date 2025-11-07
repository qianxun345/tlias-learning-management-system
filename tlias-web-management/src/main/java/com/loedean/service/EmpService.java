package com.loedean.service;

import com.loedean.pojo.Emp;
import com.loedean.pojo.EmpQueryParam;
import com.loedean.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);
//    PageResult<Emp> page(Integer page, Integer pageSize);

}
