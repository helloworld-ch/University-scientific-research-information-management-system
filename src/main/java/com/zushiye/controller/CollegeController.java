package com.zushiye.controller;


import com.zushiye.Utils.R;
import com.zushiye.pojo.*;
import com.zushiye.pojo.vo.AddUser;
import com.zushiye.service.CollegeService;
import com.zushiye.service.PersonInfoService;
import com.zushiye.service.StudentService;
import com.zushiye.service.TeacherService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zushiye
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/zushiye/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PersonInfoService personInfoService;

    /**
     * 根据高校名获取id
     */
    @RequestMapping("/queryCollageByName/{name}")
    public R queryCollageByName(@PathVariable String name){
        for (College college : collegeService.list(null)) {
            if(college.getCollegeName().equals(name)){
                return R.ok().data("collage_id",college.getCollegeId());
            }
        }
        return R.ok().data("collage_id","无该高校");
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping("addUser")
    public R addUser(@RequestBody AddUser user){
        String id = user.getId();
        String name = user.getName();
        String college_id = user.getCollege_id();
        String iden = user.getIden();
        Integer grade = user.getGrade();
        String info = user.getInfo();
        String password = user.getPassword();
        String type = user.getType();
        boolean flag = false;
        for (College college : collegeService.list(null)) {
            if(Objects.equals(college.getCollegeId(), college_id)){
                flag = true;
                break;
            }
        }
        if(!flag){
            return R.ok().message("高校号未注册").data("flag","error");
        }
        if(id.equals("")){
            return R.ok().message("id不能为空").data("flag","error");
        }
        if(name.equals("")){
            return R.ok().message("名字不能为空").data("flag","error");
        }
        if(password.equals("")){
            return R.ok().message("密码不能为空").data("flag","error");
        }

        if(Objects.equals(type, "1")){
            Teacher teacher = new Teacher();
            teacher.setTeacherIden(iden);
            if(iden.equals("")){
                return R.ok().message("身份不能为空").data("flag","error");
            }
            teacher.setTeacherId(id);
            teacher.setCollegeId(college_id);
            teacher.setTeacherName(name);
            teacher.setPassword(password);
            teacherService.save(teacher);
        }else if(Objects.equals(type, "0")){
            Student student = new Student();
            student.setGrade(grade);
            if(grade==0){
                return R.ok().message("年级不能为空").data("flag","error");
            }
            student.setStudentId(id);
            student.setStudentName(name);
            student.setCollegeId(college_id);
            student.setPassword(password);
            studentService.save(student);
        }
        PersonInfo personInfo = new PersonInfo();
        personInfo.setPersonId(id);
        personInfo.setInfo(info);
        personInfoService.save(personInfo);

        return R.ok().message("添加成功").data("flag","success");
    }

}

