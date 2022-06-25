package com.zushiye.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : zushiye
 * @create 2022/6/10 20:40
 */

@Configuration
public class AppcationBeginContoll implements SpringApplicationRunListener {
//    static ThreadPoolTaskExecutor pools = null;
    static{
        System.out.println("Nihao");
    }
}
