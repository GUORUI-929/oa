package com.buu.oa.service.impl;

import com.buu.oa.entity.EmpEmployee;
import com.buu.oa.mapper.EmpEmployeeMapper;
import com.buu.oa.service.EmpEmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工档案服务实现类
 * 处理员工档案相关业务逻辑
 */
@Service
public class EmpEmployeeServiceImpl implements EmpEmployeeService {

    @Resource
    private EmpEmployeeMapper empEmployeeMapper;

    @Override
    public List<EmpEmployee> getEmployeeList(Integer status) {
        return empEmployeeMapper.selectEmployeeList(status);
    }

    @Override
    public EmpEmployee getEmployeeById(Long id) {
        return empEmployeeMapper.selectEmployeeById(id);
    }
}