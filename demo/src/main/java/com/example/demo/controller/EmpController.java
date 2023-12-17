package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.EmpEntity;
import com.example.demo.service.EmpService;
import com.example.demo.util.Result;
import com.example.demo.vo.EmpVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api("职工管理接口")
@Validated
@RestController
@RequestMapping("vue-element-admin/emp")
public class EmpController {

    @Resource
    EmpService empService;

    @ApiOperation("获取员工列表")
    //获取员工列表
    @PostMapping("getEmpList")
    public Result<IPage<EmpEntity>> getEmpList(@RequestBody EmpVo emp) {
        IPage<EmpEntity> list = empService.selectEmpList(emp);
        return Result.success(list);
    }

    @ApiOperation("新增员工")
    //新增员工
    @PostMapping("insertEmp")
    public Result insertEmp(@RequestBody@Valid EmpEntity emp) {
        empService.insertEmp(emp);
        return Result.success();
    }

    @ApiOperation("删除员工")
    //删除员工
    @DeleteMapping("deleteEmp/{id}")
    public Result deleteEmp(@PathVariable("id") Integer id) {
        empService.deleteEmp(id);
        return Result.success();
    }

    @ApiOperation("修改员工信息")
    //修改员工信息
    @PostMapping("updateEmp")
    public Result updateEmp(@RequestBody EmpEntity emp) {
        empService.updateEmp(emp);
        return Result.success();
    }
}
