package com.buu.oa.common;

import lombok.Data;

/**
 * 统一返回结果封装
 * 所有接口统一返回格式，包含状态码、提示信息、返回数据
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 成功返回
     * @param data 返回数据
     * @return 封装后的成功结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败返回
     * @param msg 错误提示信息
     * @return 封装后的失败结果
     */
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }
}