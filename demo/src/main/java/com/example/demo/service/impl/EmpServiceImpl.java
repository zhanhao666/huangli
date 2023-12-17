package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.EmpEntity;
import com.example.demo.service.EmpService;
import com.example.demo.dao.EmpDao;
import com.example.demo.vo.EmpVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("empService")
public class EmpServiceImpl extends ServiceImpl<EmpDao, EmpEntity> implements EmpService {

    @Resource
    EmpDao empDao;

    @Override
    public IPage<EmpEntity> selectEmpList(EmpVo vo) {
        Page<EmpEntity> page = new Page<>(vo.getCurrentPage(),vo.getPageSize());
        QueryWrapper<EmpEntity> queryWrapper = new QueryWrapper<EmpEntity>();
        if (!StringUtils.isEmpty(vo.getName())){
            queryWrapper = queryWrapper.like("emp_name",vo.getName());
        }
        if (!StringUtils.isEmpty(vo.getDept())){
            queryWrapper = queryWrapper.eq("dept_name",vo.getDept());
        }
        if (!StringUtils.isEmpty(vo.getEducation())){
            queryWrapper = queryWrapper.eq("emp_degree_name",vo.getEducation());
        }
        queryWrapper = queryWrapper.orderBy(!StringUtils.isEmpty(vo.getSort()),"1".equals(vo.getSort()),"id");
        return empDao.selectPage(page,queryWrapper);
    }

    @Override
    @Transactional
    public void insertEmp(EmpEntity emp) {
        empDao.insert(emp);
    }

    @Override
    @Transactional
    public void deleteEmp(Integer id) {
        empDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateEmp(EmpEntity emp) {
        empDao.updateById(emp);
    }
}
