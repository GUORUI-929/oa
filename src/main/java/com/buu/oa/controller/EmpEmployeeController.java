package com.buu.oa.controller;

import com.buu.oa.common.Result;
import com.buu.oa.entity.EmpEmployee;
import com.buu.oa.service.EmpEmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 员工档案控制器
 * 处理工作台员工档案相关前端请求，包含列表查询、详情查询接口
 */
@RestController
@RequestMapping("/api/employee")
public class EmpEmployeeController {

    @Resource
    private EmpEmployeeService empEmployeeService;

    /**
     * 获取员工档案列表
     * @param status 员工状态（可选，1在职 2试用期 3离职）
     * @return 员工档案列表
     */
    @GetMapping("/list")
    public Result<List<EmpEmployee>> getEmployeeList(Integer status) {
        List<EmpEmployee> list = empEmployeeService.getEmployeeList(status);
        return Result.success(list);
    }

    /**
     * 根据ID获取员工详情
     * @param id 员工ID
     * @return 员工档案详情
     */
    @GetMapping("/{id}")
    public Result<EmpEmployee> getEmployeeDetail(@PathVariable Long id) {
        EmpEmployee employee = empEmployeeService.getEmployeeById(id);
        if (employee == null) {
            return Result.error("员工信息不存在");
        }
        return Result.success(employee);
    }
}