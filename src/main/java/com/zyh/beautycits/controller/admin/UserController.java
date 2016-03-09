package com.zyh.beautycits.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.user.UserService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("/admin")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 判断是否登录
		if (!isLogin()) {
			String url_login = getUrl_BizFunc("login", "index.html");
			mav.setViewName("redirect:"+url_login);
			return mav;
		}
		// 获取所有用户信息
		ResultMsg resultMsg = userService.getUsers();
		if (resultMsg.getState() == Results.ERROR) {
			goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("users", JSON.toJSONString(resultMsg.getMsgEntity()));
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		mav.setViewName("/admin_users");
        return mav;
    }
}
