package com.zushiye.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zushiye.Utils.R;
import com.zushiye.pojo.Research;
import com.zushiye.pojo.Return.StudentReturn;
import com.zushiye.pojo.Return.TeacherReturn;
import com.zushiye.pojo.Student;
import com.zushiye.pojo.StudentResearch;
import com.zushiye.pojo.Teacher;
import com.zushiye.pojo.vo.StudentQuery;
import com.zushiye.pojo.vo.TeacherQuery;
import com.zushiye.service.CollegeService;
import com.zushiye.service.StudentResearchService;
import com.zushiye.service.StudentService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zushiye
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/zushiye/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private StudentResearchService studentResearchService;

    /**
     * 根据limit,current分页查询
     */
    @RequestMapping("/getPageById/{current}/{limit}")
    public R getPageById(@PathVariable long current,
                         @PathVariable long limit,
                         @RequestBody(required = false) StudentQuery teacherQuery){
        Page<Student> studentPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        String id  = teacherQuery.getId();
        Integer grade = teacherQuery.getGrade();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("student_name",name);//模糊查询
        }
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("student_id",id);
        }
        if(!StringUtils.isEmpty(grade)){
            wrapper.eq("grade",grade);
        }

        studentService.page(studentPage,wrapper);

        long total = studentPage.getTotal();
        List<Student> records = studentPage.getRecords();
        List<StudentReturn> list = new ArrayList<>();
        for (Student record : records) {
            QueryWrapper<StudentResearch> w = new QueryWrapper<>();
            if(!StringUtils.isEmpty(record.getStudentId())){
                w.eq("student_id",record.getStudentId());
            }
            StudentReturn studentReturn = new StudentReturn();
            studentReturn.setId(record.getStudentId());
            studentReturn.setGrade(record.getGrade());
            studentReturn.setName(record.getStudentName());
            studentReturn.setCollage(collegeService.getById(record.getCollegeId()).getCollegeName());
            studentReturn.setNumber(studentResearchService.count(w));

            list.add(studentReturn);
        }
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",list);

        return R.ok().data(map);
    }

    /**
     * 更据researchId获取研究内容
     */
    @RequestMapping("/getStudentByResearchId/{id}")
    public R getStudentByResearch(@PathVariable String id){
        List<String> list = new ArrayList<>();
        QueryWrapper<StudentResearch> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("research_id",id);
        }
        for (StudentResearch studentResearch : studentResearchService.list(wrapper)) {
            list.add(studentService.getById(studentResearch.getStudentId()).getStudentName());
        }
        return R.ok().data("list",list);
    }


}

