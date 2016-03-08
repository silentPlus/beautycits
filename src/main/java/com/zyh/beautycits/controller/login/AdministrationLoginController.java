package com.zyh.beautycits.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.user.User;

/**
 * 
 * @ClassName: LoginController
 * @Description: 后台管理系统登录
 *
 */
@Controller
@RequestMapping("/administration")
public class AdministrationLoginController extends BaseController {
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = getUrl_BizFunc("administration", "login.html");
		mav.addObject("loginUrl", url);
		mav.setViewName("/sign_in");
        return mav;
    }
	
	@RequestMapping(value = "/login.html")
    public JsonPackage login(HttpServletRequest request, HttpServletResponse response, User user){
		JsonPackage jsonPackage = new JsonPackage();
		return jsonPackage;
	}
}
