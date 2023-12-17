package com.example.demo.vo;

import lombok.Data;


@Data
public class EmpVo {

    private String name;
    private String dept;
    private String education;
    private String sort;
    private Long currentPage;
    private Long pageSize;
}
