package com.zushiye.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zushiye.Utils.R;
import com.zushiye.pojo.Introduce;
import com.zushiye.pojo.Research;
import com.zushiye.pojo.Return.ResearchReturn;
import com.zushiye.pojo.Return.TeacherReturn;
import com.zushiye.pojo.StudentResearch;
import com.zushiye.pojo.Teacher;
import com.zushiye.pojo.vo.ResearchQuery;
import com.zushiye.pojo.vo.RuleForm;
import com.zushiye.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zushiye
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/zushiye/research")
public class ResearchController {
    //控制编号是否刷新
    private static String date;
    private static int num = 0;

    @Autowired
    private ResearchService researchService;

    @Autowired
    private StudentResearchService studentResearchService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private IntroduceService introduceService;

    /**
     * 更据current,limit获取科研信息
     */
    @RequestMapping("/getPageById/{current}/{limit}")
    public R getPageById(@PathVariable long current,
                         @PathVariable long limit,
                         @RequestBody(required = false) ResearchQuery teacherQuery){
        Page<Research> researchPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Research> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        String id  = teacherQuery.getId();
//        String iden = teacherQuery.getTeacher();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("research_title",name);//模糊查询
        }
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("research_id",id);
        }
//        if(!StringUtils.isEmpty(iden)){
//
//        }

        researchService.page(researchPage,wrapper);

        long total = researchPage.getTotal();
        List<Research> records = researchPage.getRecords();
        List<ResearchReturn> list = new ArrayList<>();
        for (Research record : records) {
            QueryWrapper<StudentResearch> w = new QueryWrapper<>();
            if(!StringUtils.isEmpty(record.getResearchId())){
                w.eq("research_id",record.getResearchId());
            }
            ResearchReturn researchReturn = new ResearchReturn();
            researchReturn.setId(record.getResearchId());
            researchReturn.setName(record.getResearchTitle());
            researchReturn.setNumber(studentResearchService.count(w));
            researchReturn.setTeacher(teacherService.getById(record.getTeacherId()).getTeacherName());
            researchReturn.setType(typeService.getById(record.getTypeId()).getTypeName());

            list.add(researchReturn);
        }
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",list);

        return R.ok().data(map);
    }

    /**
     * 添加科研
     */
    @RequestMapping("/addResearch")
    public R addResearch(@RequestBody(required = false)RuleForm ruleForm){
        String name = ruleForm.getName();
        String type = ruleForm.getType();
        String teacher = ruleForm.getTeacher();
        String info = ruleForm.getInfo();

        if(teacherService.getById(teacher)==null){
            return R.ok().message("没有该导师").data("flag","error");
        }

        // 指定格式化格式
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String format = f.format(new Date());
        if(!format.equals(date)){
            num=0;
            date = format;
        }
        StringBuilder id = new StringBuilder("" + num);
        for(int i = id.length();i<6;i++){
            id.insert(0, "0");
        }
        String research_id = format+id;
        num++;

        Research research = new Research();
        research.setResearchId(research_id);
        research.setResearchTitle(name);
        research.setTeacherId(teacher);
        research.setTypeId(type);

        Introduce introduce = new Introduce();
        introduce.setIntroduceId(research_id);
        introduce.setInfo(info);
        introduceService.save(introduce);
        researchService.save(research);
        return R.ok().message("添加成功").data("flag","success");
    }



}

