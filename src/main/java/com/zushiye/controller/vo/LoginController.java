package com.zushiye.controller.vo;

import com.zushiye.Utils.H;
import com.zushiye.Utils.R;
import com.zushiye.handle.FaceSpot;
import com.zushiye.pojo.vo.LoginUser;
import com.zushiye.service.StudentService;
import com.zushiye.service.TeacherService;
import com.zushiye.service.vo.LoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author : zushiye
 * @create 2022/5/16 11:22
 */
@RestController
@CrossOrigin
@RequestMapping("/zushiye/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public R login(@RequestBody LoginUser loginUser){
        final H login = loginService.login(loginUser);
        if(login.getSuccess()){
            //签发token
//            String role = (String) login.getData().get("roles");
//            String token = JwtUtil.getToken(loginUser.getUsername(),role);
//            return R.ok().data("token",token).data("roles",role);
            return R.ok().data("id",login.getData());
        }
        return R.error().message("账号密码不对");
    }

    /**
     * 更据id获取用户信息
     * @param id
     * @return
     */
    @RequestMapping("getInfo/{id}")
    public R getInfo(@PathVariable String id) throws ServletException {
//        JwtUtil.checkToken(token);
//        final String userId = JwtUtil.getUserId(token);
//        final String role = JwtUtil.getRole(token);
//
//        if(Objects.equals(role, "student")){
//            final Student byId = studentService.getById(userId);
//            return R.ok().data("user",byId);
//        }else if(role.equals("teacher")){
//            final Teacher byId = teacherService.getById(userId);
//            return R.ok().data("user",byId);
//        }
//        return R.ok();
        return R.ok();
    }

    @RequestMapping("/searchFace")
    public R searchFace(@RequestBody String img) {
        JSONObject js = FaceSpot.searchFace(img,"face", "admin");
        return R.ok().data("data",js.toString());
    }
}
