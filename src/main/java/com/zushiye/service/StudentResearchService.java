package com.zushiye.service;

import com.zushiye.pojo.StudentResearch;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zushiye
 * @since 2022-05-16
 */
public interface StudentResearchService extends IService<StudentResearch> {

    /**
     * 根据id获取学生人数
     */
    Integer getNumberById(String id);
}
