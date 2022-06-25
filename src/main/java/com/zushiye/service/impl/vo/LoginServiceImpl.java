package com.zushiye.service.impl.vo;

import com.zushiye.Utils.H;
import com.zushiye.mapper.StudentMapper;
import com.zushiye.mapper.TeacherMapper;
import com.zushiye.pojo.Student;
import com.zushiye.pojo.Teacher;
import com.zushiye.pojo.vo.LoginUser;
import com.zushiye.service.StudentService;
import com.zushiye.service.TeacherService;
import com.zushiye.service.vo.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : zushiye
 * @create 2022/5/23 20:20
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;


    /**
     * 用户登录
     * @param loginUser
     * @return
     */
    @Override
    public H login(LoginUser loginUser) {
        //直接查询是否是系统管理员
        if(loginUser.getUsername().equals("admin")&&loginUser.getPassword().equals("system")){
            return H.ok().data("id","admin");
        }
        final Teacher teacher = teacherService.getById(loginUser.getUsername());
        final Student student = studentService.getById(loginUser.getUsername());
        //先查询用户
        if(null!=student) {
            //查询密码
            if(Objects.equals(student.getPassword(), loginUser.getPassword())){
                return H.ok().data("roles","student").data("id",student.getStudentId());
            }else{
                return H.error().message("密码错误");
            }
        }else if(null!=teacher){
            //查询密码
            if(Objects.equals(teacher.getPassword(), loginUser.getPassword())){
                return H.ok().data("roles","teacher").data("id",teacher.getTeacherId());
            }else{
                return H.error().message("密码错误");
            }
        }
        return H.error().message("无该用户");
    }
}
