package com.loedean.service;

import com.loedean.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);

    void save(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
