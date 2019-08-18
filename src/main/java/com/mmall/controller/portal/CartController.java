package com.mmall.controller.portal;

import com.alibaba.fastjson.JSON;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.service.IUserService;
import com.mmall.service.RedisService;
import com.mmall.utils.CookieUtil;
import com.mmall.vo.CartVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICartService iCartService;

    @Autowired
    private RedisService redisService;


    @ResponseBody
    @RequestMapping(value = "add.do" ,method = RequestMethod.GET)
    public ServerResponse<CartVo> add(HttpServletRequest request, Integer count, Integer productId){
        if(productId==null || count==null){
            return ServerResponse.createByErrorCodeIllegaArg();
        }
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        return iCartService.add(user.getId(),productId,count);

    }

    @ResponseBody
    @RequestMapping(value = "update.do" ,method = RequestMethod.GET)
    public ServerResponse<CartVo> update(HttpServletRequest request, Integer count, Integer productId){
        if(productId==null || count==null){
            return ServerResponse.createByErrorCodeIllegaArg();
        }
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        return iCartService.update(user.getId(),productId,count);

    }

    @ResponseBody
    @RequestMapping(value = "delete_product.do" ,method = RequestMethod.GET)
    public ServerResponse<CartVo> deleteProduct(HttpServletRequest request, String productIds){

        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        return iCartService.deleteProduct(user.getId(),productIds);
    }

    @ResponseBody
    @RequestMapping(value = "list.do" ,method = RequestMethod.GET)
    public ServerResponse<CartVo> list(HttpServletRequest request){

        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        return iCartService.list(user.getId());
    }

    /**
     * 全选
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "select_all.do" ,method = RequestMethod.GET)
    public ServerResponse<CartVo> selectAll(HttpServletRequest request){

        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        return iCartService.selectOrUnSelect(user.getId(),null, Const.Cart.CHECKED);
    }

    /**
     * 全反选
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "un_select_all.do" ,method = RequestMethod.GET)
    public ServerResponse<CartVo> unSelectAll(HttpServletRequest request){

        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        return iCartService.selectOrUnSelect(user.getId(),null, Const.Cart.UN_CHECKED);
    }


    /**
     * 单独选
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "select.do" ,method = RequestMethod.GET)
    public ServerResponse<CartVo> list(HttpServletRequest request, Integer productId){

        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        return iCartService.selectOrUnSelect(user.getId(),productId, Const.Cart.CHECKED);
    }

    /**
     * 单独反选
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "un_select.do" ,method = RequestMethod.GET)
    public ServerResponse<CartVo> unSelect(HttpServletRequest request, Integer productId){

        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createByErrorCodeNEEDLOGIN("用户未登录，请登录");
        }
        return iCartService.selectOrUnSelect(user.getId(),productId, Const.Cart.UN_CHECKED);
    }

    /**
     * 查询用户的购物车产品数量，数量有10个，返回10个
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get_cart_product_count.do" ,method = RequestMethod.GET)
    public ServerResponse<Integer> getCartProductCount(HttpServletRequest request){

        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法返回当前用户信息");
        }
        String userJsonStr = redisService.get(loginToken);
        User user = (User) JSON.parseObject(userJsonStr,User.class);
        if(user==null){
            return ServerResponse.createBySuccess(0);
        }
        return iCartService.getCartProductCount(user.getId());
    }

}
