package com.zushiye.service.vo;

import com.zushiye.Utils.H;
import com.zushiye.pojo.vo.LoginUser;

public interface LoginService {
    H login(LoginUser loginUser);
}
