package com.zushiye.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zushiye.Utils.R;
import com.zushiye.pojo.Result;
import com.zushiye.pojo.Return.ResultReturn;
import com.zushiye.pojo.Return.StudentReturn;
import com.zushiye.pojo.Student;
import com.zushiye.pojo.StudentResearch;
import com.zushiye.pojo.vo.ResultQuery;
import com.zushiye.pojo.vo.StudentQuery;
import com.zushiye.service.ResearchService;
import com.zushiye.service.ResultService;
import com.zushiye.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/zushiye/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private ResearchService researchService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 根据limit,current分页查询
     */
    @RequestMapping("/getPageById/{current}/{limit}")
    public R getPageById(@PathVariable long current,
                         @PathVariable long limit,
                         @RequestBody(required = false) ResultQuery resultQuery){
        Page<Result> studentPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Result> wrapper = new QueryWrapper<>();
        Integer version = resultQuery.getV();
        String id  = resultQuery.getId();
        Integer isIssue = resultQuery.getIsIssue();

        if(!StringUtils.isEmpty(id)){
            wrapper.like("research_id",id);//模糊查询
        }
        if(!StringUtils.isEmpty(isIssue)){
            wrapper.eq("is_issue",isIssue);
        }
        if(!StringUtils.isEmpty(version)){
            wrapper.eq("version",version);
        }

        resultService.page(studentPage,wrapper);

        long total = studentPage.getTotal();
        List<Result> records = studentPage.getRecords();
        List<ResultReturn> list = new ArrayList<>();
        for (Result record : records) {
            ResultReturn resultReturn = new ResultReturn();
            resultReturn.setId(record.getResearchId());
            resultReturn.setTitle(record.getContent());
            resultReturn.setTeacher(researchService.getById(record.getResearchId()).getTeacherId());
            resultReturn.setTeacherName(teacherService.getById(resultReturn.getTeacher()).getTeacherName());
            resultReturn.setIsIssue(record.getIsIssue());
            resultReturn.setV(record.getVersion());
            list.add(resultReturn);
        }
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",list);

        return R.ok().data(map);
    }

    /**
     * 发布版本
     */
    @RequestMapping("issueProject/{id}/{version}")
    public R issueProject(@PathVariable String id,
                          @PathVariable Integer version){
        QueryWrapper<Result> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("research_id",id);
        }
        List<Result> results = resultService.list(wrapper);
        for (Result result : results) {
            QueryWrapper<Result> w = new QueryWrapper<>();
            if(!Objects.equals(result.getVersion(), version)){
                if(result.getIsIssue()!=0){
                    if(!StringUtils.isEmpty(id)){
                        w.eq("research_id",id);
                    }
                    if(!StringUtils.isEmpty(result.getVersion())){
                        w.eq("version",result.getVersion());
                    }
                    result.setIsIssue(0);
                    resultService.update(result,w);
                }
            }else{
                if(result.getIsIssue()==0){
                    if(!StringUtils.isEmpty(id)){
                        w.eq("research_id",id);
                    }
                    if(!StringUtils.isEmpty(result.getVersion())){
                        w.eq("version",result.getVersion());
                    }
                    result.setIsIssue(1);
                    resultService.update(result,w);
                }
            }
        }
        return R.ok().message("发布成功，其他版本已下架");
    }

    /**
     * 删除版本
     */
    @RequestMapping("removeProject/{id}/{version}")
    public R removeProject(@PathVariable String id,
                           @PathVariable Integer version){
        QueryWrapper<Result> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("research_id",id);
        }
        if(!StringUtils.isEmpty(version)){
            wrapper.eq("version",version);
        }
        resultService.remove(wrapper);
        return R.ok().message("删除成功");
    }

    /**
     * 下架版本
     */
    @RequestMapping("/downProject/{id}/{version}")
    public R downProject(@PathVariable String id,
                         @PathVariable Integer version){

        QueryWrapper<Result> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("research_id",id);
        }
        if(!StringUtils.isEmpty(version)){
            wrapper.eq("version",version);
        }
        Result one = resultService.getOne(wrapper);
        one.setIsIssue(0);
        resultService.update(one,wrapper);
        return R.ok().message("下架成功");
    }

}

