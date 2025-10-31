package com.loedean.service.impl;

import com.loedean.mapper.EmpLogMapper;
import com.loedean.pojo.EmpLog;
import com.loedean.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {


    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
