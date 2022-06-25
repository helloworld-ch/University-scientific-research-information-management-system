package com.zushiye.exceptionHandle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : zushiye
 * @create 2021/11/28 17:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
