package com.zushiye.service.impl;

import com.zushiye.pojo.Student;
import com.zushiye.mapper.StudentMapper;
import com.zushiye.service.StudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
