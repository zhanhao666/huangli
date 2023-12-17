package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@TableName("emp")
public class EmpEntity {


    @TableId(value = "id",type = IdType.INPUT)
    @Null(message = "新增不可主动指定id")
    private Integer id;
    @NotBlank(message="员工姓名不能为空")
    private String empName;
    @NotBlank(message="员工性别不能为空")
    private String sex;
    @NotNull(message = "员工年齡不能为空")
    private Integer age;
    @NotBlank(message="员工部门不能为空")
    private String deptName;
    @NotBlank(message="员工学历不能为空")
    private String empDegreeName;
}
