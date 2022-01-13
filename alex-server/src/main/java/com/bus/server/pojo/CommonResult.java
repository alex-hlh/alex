package com.bus.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: RespBean
 * @Description:
 * @Author: hlh
 * @Date: 2021-12-25 13:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult {
    private long code;
    private String message;
    private Object data;

    /**
     * 成功返回
     *
     * @param message
     * @return
     */
    public static CommonResult success(String message) {
        return new CommonResult(200, message, null);
    }


    /**
     * 成功返回
     *
     * @param message
     * @param obj
     * @return
     */
    public static CommonResult success(String message, Object obj) {
        return new CommonResult(200, message, obj);
    }

    /**
     * 失败结果返回
     *
     * @param message
     * @return
     */
    public static CommonResult error(String message) {
        return new CommonResult(500, message, null);
    }

    /**
     * 失败结果返回
     *
     * @param message
     * @return
     */
    public static CommonResult error(String message, Object obj) {
        return new CommonResult(500, message, obj);
    }

}
