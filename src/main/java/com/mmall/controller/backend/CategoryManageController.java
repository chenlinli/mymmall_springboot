package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @ResponseBody
    @RequestMapping(value = "add_category.do",method = RequestMethod.POST)
    public ServerResponse<String> addCategory(HttpServletRequest request, String categoryName,
                                              @RequestParam(value = "parentId",defaultValue = "0") int parentId){
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
            return iCategoryService.addCategory(categoryName,parentId);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
*/
        //拦截器处理判断用户挡路和权限逻辑
        return iCategoryService.addCategory(categoryName,parentId);

    }


    @ResponseBody
    @RequestMapping(value = "set_category_name.do",method = RequestMethod.POST)
    public ServerResponse<String> setCategoryName(HttpServletRequest request, Integer categoryId, String categoryName){
        return iCategoryService.setCategoryName(categoryId,categoryName);
    }

    @ResponseBody
    @RequestMapping(value = "get_category.do",method = RequestMethod.POST)
    public ServerResponse<List<Category>> getChildrenParallelCategory(HttpServletRequest request,
                                                                      @RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        return iCategoryService.getChildrenParallelCategory(categoryId);

    }

    /**
     *   //查询当前结点和递归子节点的id
     * @param request
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "get_deep_category.do",method = RequestMethod.POST)
    public ServerResponse<List<Integer>> getCategoryAndDeepChildrenCategory(HttpServletRequest request,
                                                                            @RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
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
        if(iUserService.checkAdminRole(user).isSuccess()) {
            //查询当前结点和递归子节点的id
            return iCategoryService.getCategoryAndChildrenById(categoryId);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }*/
        return iCategoryService.getCategoryAndChildrenById(categoryId);
    }

}

