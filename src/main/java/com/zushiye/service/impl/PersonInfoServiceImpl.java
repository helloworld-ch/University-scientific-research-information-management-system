package com.zushiye.service.impl;

import com.zushiye.pojo.PersonInfo;
import com.zushiye.mapper.PersonInfoMapper;
import com.zushiye.service.PersonInfoService;
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
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoMapper, PersonInfo> implements PersonInfoService {

}
