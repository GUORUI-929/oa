package com.buu.oa.service;

import com.buu.oa.entity.EmpEmployee;

import java.util.List;

/**
 * 员工档案服务接口
 * 定义员工档案查询、详情等业务方法
 */
public interface EmpEmployeeService {

    /**
     * 获取员工档案列表
     * @param status 员工状态（可选）
     * @return 员工档案列表
     */
    List<EmpEmployee> getEmployeeList(Integer status);

    /**
     * 根据ID获取员工详情
     * @param id 员工ID
     * @return 员工档案详情
     */
    EmpEmployee getEmployeeById(Long id);
}