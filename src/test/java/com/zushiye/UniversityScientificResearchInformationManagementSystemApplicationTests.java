package com.zushiye;

import com.zushiye.handle.JwtUtil;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class UniversityScientificResearchInformationManagementSystemApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void JwtTest() throws ServletException {
        String ok = JwtUtil.getToken("20200202921","admin");
        System.out.println(ok);
        JwtUtil.checkToken(ok);
        System.out.println(JwtUtil.getUserId(ok));
        System.out.println(JwtUtil.getRole(ok));
    }

    @Test
    public void test(){
        Date now = new Date(); // 创建一个Date对象，获取当前时间
        // 指定格式化格式
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        System.out.println(f.format(now)); // 将当前时间袼式化为指定的格式
    }

    @Test
    public void test01(){
        int num = 0;
        String date = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String format = f.format(new Date());
        if(format.equals(date)){
            num=0;
        }else{
            date = format;
        }
        StringBuilder id = new StringBuilder("" + num);
        for(int i = id.length();i<6;i++){
            id.insert(0, "0");
        }
        String research_id = format+id;
        System.out.println(research_id);
    }

}
