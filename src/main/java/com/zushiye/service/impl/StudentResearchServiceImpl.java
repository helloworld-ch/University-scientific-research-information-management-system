package com.zushiye.service.impl;

import com.zushiye.pojo.StudentResearch;
import com.zushiye.mapper.StudentResearchMapper;
import com.zushiye.service.StudentResearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zushiye
 * @since 2022-05-16
 */
@Service
public class StudentResearchServiceImpl extends ServiceImpl<StudentResearchMapper, StudentResearch> implements StudentResearchService {

    @Override
    public Integer getNumberById(String id) {
        return null;
    }
}
