package com.zushiye.Utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : zushiye
 * @create 2022/5/23 20:21
 */

@Data
public class H {
    private Boolean success;
    private String message;
    private Map<String, Object> data = new HashMap<String, Object>();

    private H (){
    }

    //成功静态方法
    public static H ok() {
        H r = new H();
        r.setMessage("成功");
        r.setSuccess(true);
        return r;
    }

    //失败静态方法
    public static H error() {
        H r = new H();
        r.setMessage("失败");
        r.setSuccess(false);
        return r;
    }

    public H data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public H data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public H message(String message){
        this.setMessage(message);
        return this;
    }
}
