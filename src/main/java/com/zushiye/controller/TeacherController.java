package com.zushiye.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zushiye.Utils.R;
import com.zushiye.pojo.Research;
import com.zushiye.pojo.Return.TeacherReturn;
import com.zushiye.pojo.Teacher;
import com.zushiye.pojo.vo.TeacherQuery;
import com.zushiye.service.CollegeService;
import com.zushiye.service.IdenService;
import com.zushiye.service.ResearchService;
import com.zushiye.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/zushiye/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private IdenService idenService;

    @Autowired
    private ResearchService researchService;

    /**
     * 根据limit,current分页查询
     */
    @RequestMapping("/getPageById/{current}/{limit}")
    public R getPageById(@PathVariable long current,
                         @PathVariable long limit,
                         @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<Teacher> teacherPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        String id  = teacherQuery.getId();
        String iden = teacherQuery.getIden();

        System.out.println(teacherQuery.toString());
        if(!StringUtils.isEmpty(name)){
            wrapper.like("teacher_name",name);//模糊查询
        }
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("teacher_id",id);
        }
        if(!StringUtils.isEmpty(iden)){
            wrapper.eq("teacher_iden",iden);
        }

        teacherService.page(teacherPage,wrapper);

        long total = teacherPage.getTotal();
        List<Teacher> records = teacherPage.getRecords();
        List<TeacherReturn> list = new ArrayList<>();
        for (Teacher record : records) {
            QueryWrapper<Research> w = new QueryWrapper<>();
            if(!StringUtils.isEmpty(record.getTeacherId())){
                w.eq("teacher_id",record.getTeacherId());
            }
            TeacherReturn teacherReturn = new TeacherReturn();
            teacherReturn.setId(record.getTeacherId());
            teacherReturn.setIden(idenService.getById(record.getTeacherIden()).getIdenName());
            teacherReturn.setName(record.getTeacherName());
            teacherReturn.setCollage(collegeService.getById(record.getCollegeId()).getCollegeName());
            teacherReturn.setNumber(researchService.count(w));

            list.add(teacherReturn);
        }
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",list);

        return R.ok().data(map);
    }

    /**
     * 更据研究获取导师
     */
    @RequestMapping("getTeacherByResearchId/{id}")
    public R getTeacherByResearchId(@PathVariable String id){
        List<String> list = new ArrayList<>();
        QueryWrapper<Research> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("research_id",id);
        }
        for (Research research : researchService.list(wrapper)) {
            list.add(teacherService.getById(research.getTeacherId()).getTeacherName());
        }
        return R.ok().data("list",list);
    }
}

