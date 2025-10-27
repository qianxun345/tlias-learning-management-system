package com.loedean.mapper;

import com.loedean.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select count(*) from emp")
    Long count();

    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
            "order by emp.update_time desc limit #{start}, #{pageSize}")
    List<Emp> list(Integer start, Integer pageSize);
}
