package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.EmpEntity;
import com.example.demo.vo.EmpVo;

import java.util.List;

public interface EmpService extends IService<EmpEntity> {

    //查询员工列表
    IPage<EmpEntity> selectEmpList(EmpVo empVo);

    void insertEmp(EmpEntity emp);

    void deleteEmp(Integer id);

    void updateEmp(EmpEntity emp);
}
