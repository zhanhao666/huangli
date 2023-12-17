package com.example.demo.util;

import lombok.Data;

/**
 * 全局返回同一结果封装类
 */
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;


    private Result(){}

    public static <T> Result<T> build(T data){
        Result<T> result=new Result();
        if(data!=null){
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T data,Integer code,String msg){
        Result<T> build = build(data);
        build.setCode(code);
        build.setMessage(msg);
        return build;
    }

    public static <T> Result<T> build(T data,ResultCodeEnum codeEnum){
        Result<T> build = build(data);
        build.setMessage(codeEnum.getMessage());
        build.setCode(codeEnum.getCode());
        return build;
    }

    //成功
    public static <T> Result<T> success(){
        return build(null,ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> success(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }

    //失败
    public static <T> Result<T> fail(){
        return build(null,ResultCodeEnum.FAIL);
    }
    public static <T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}

