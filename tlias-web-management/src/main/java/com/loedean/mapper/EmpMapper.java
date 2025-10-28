package com.loedean.mapper;

import com.loedean.pojo.Emp;
import com.loedean.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc")
    List<Emp> list(EmpQueryParam empQueryParam);

}
