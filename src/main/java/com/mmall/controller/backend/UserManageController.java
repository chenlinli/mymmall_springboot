package com.mmall.controller.backend;

import com.alibaba.fastjson.JSON;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.service.RedisService;
import com.mmall.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisService redisService;
    @ResponseBody
    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public ServerResponse<User> login(String username, String password, HttpSession httpSession,
                                      HttpServletResponse httpServletResponse){
        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            User user = response.getData();
            if(user.getRole().equals(Const.Role.ROLL_ADMIN)){
                CookieUtil.writeLoginToken(httpServletResponse,httpSession.getId());
                redisService.setEx(httpSession.getId(), JSON.toJSONString(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_TIME);
                return response;
            }else{
                return ServerResponse.createByErrorMessage("不是管理员无法登陆");
            }

        }
        return response;
    }

}
