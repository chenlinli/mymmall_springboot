package com.mmall.controller.portal;


import com.alibaba.fastjson.JSON;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.service.RedisService;
import com.mmall.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisService redisService;
    /**
     * 登录
     *
     * @param username
     * @param password
     * @param httpSession
     * @return
     */
    @GetMapping(value = "/login.do")
    public ServerResponse<User> login(String username, String password, HttpSession httpSession,
                                      HttpServletResponse httpServletResponse) {
        ServerResponse<User> result = iUserService.login(username, password);
        if (result.isSuccess()) {
            CookieUtil.writeLoginToken(httpServletResponse,httpSession.getId());
            redisService.setEx(httpSession.getId(), JSON.toJSONString(result.getData()), Const.RedisCacheExtime.REDIS_SESSION_TIME);
        }
        return result;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {
        String loginToken = CookieUtil.readLoginToken(request);
        CookieUtil.delLoginToken(request,response);
        redisService.del(loginToken);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }

    /**
     * 用户名email/是否存在
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "/check_valid.do", method = RequestMethod.GET)
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    @RequestMapping(value = "/get_user_info.do", method = RequestMethod.GET)
    public ServerResponse<User> getUserInfo( HttpServletRequest request) {
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");

    }

    @RequestMapping(value = "/forget_get_question.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }

    @RequestMapping(value = "/forget_check_answer.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        //缓存有效期
        return iUserService.checkAnswer(username, question, answer);
    }

    @RequestMapping(value = "/forget_reset_password.do", method = RequestMethod.GET)
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken){
        return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
    }

    @RequestMapping(value = "/reset_password.do", method = RequestMethod.GET)
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, HttpServletRequest request){
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }


    @RequestMapping(value = "/update_information.do", method = RequestMethod.POST)
    public ServerResponse<User> updateInformation(HttpServletRequest request, User user){
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法更新用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User currentUser = (User) JSON.parseObject(userJsonStr,User.class);
        if(currentUser==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if(response.isSuccess()){
            response.getData().setUsername(currentUser.getUsername());
            redisService.setEx(loginToken, JSON.toJSONString(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_TIME);
        }
        return response;
    }

    @RequestMapping(value = "/get_information.do", method = RequestMethod.GET)
    public ServerResponse<User> getInformation(HttpServletRequest request){
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User currentUser = (User) JSON.parseObject(userJsonStr,User.class);
        if(currentUser==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录,无法获取当前用户信息,status=10,强制登录");
        }
        return iUserService.getInfomation(currentUser.getId());
    }
}

