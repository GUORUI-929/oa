package com.buu.oa.mapper;

import com.buu.oa.entity.EmpEmployee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工档案Mapper接口
 * 定义员工档案相关数据库操作方法
 */
public interface EmpEmployeeMapper {

    /**
     * 查询全部员工档案列表（联表获取部门名称）
     * @param status 员工状态（可选，为空查询全部）
     * @return 员工档案列表
     */
    List<EmpEmployee> selectEmployeeList(@Param("status") Integer status);

    /**
     * 根据ID查询员工详情
     * @param id 员工ID
     * @return 员工档案详情
     */
    EmpEmployee selectEmployeeById(@Param("id") Long id);
}