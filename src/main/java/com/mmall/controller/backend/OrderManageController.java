package com.mmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.IOrderService;
import com.mmall.service.IUserService;
import com.mmall.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manage/order")
public class OrderManageController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpServletRequest request,
                                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                              @RequestParam(value = "pageNum",defaultValue = "1") int pageNum){

      /*  String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iOrderService.manageList(pageSize,pageNum);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }*/
        return iOrderService.manageList(pageSize,pageNum);
    }

     @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(HttpServletRequest request, long orderNo){

       /*  String loginToken = CookieUtil.readLoginToken(request);
         if(StringUtils.isEmpty(loginToken)){
             return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
         }
         String userJsonStr = RedisShardedPoolUtil.get(loginToken);
         User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iOrderService.manageDetail(orderNo);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }*/
         return iOrderService.manageDetail(orderNo);
    }

    /**
     * 精确搜索
     * @param request
     * @param orderNo
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpServletRequest request, long orderNo,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                                @RequestParam(value = "pageNum",defaultValue = "1") int pageNum){

    /*    String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iOrderService.manageSearch(orderNo,pageNum,pageSize);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }*/
        return iOrderService.manageSearch(orderNo,pageNum,pageSize);
    }

    /**
     * 发货
     * @param request
     * @param orderNo
     * @return
     */
    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpServletRequest request, long orderNo){

       /* String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        //校验是否管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //管理员
            return iOrderService.manageSendGoods(orderNo);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }*/
        return iOrderService.manageSendGoods(orderNo);
    }

}
