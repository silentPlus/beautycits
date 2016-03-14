package com.zyh.beautycits.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.user.UserService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.SessionObject;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String url = getUrl_BizFunc("login", "dologin.html");
		mav.addObject("loginUrl", url);
		mav.setViewName("/login");
        return mav;
    }
	
	@RequestMapping(value = "/dologin.html")
    public JsonPackage doLogin(HttpServletRequest request, HttpServletResponse response, User user){
		JsonPackage jsonPackage = new JsonPackage();
		ResultMsg resultMsg = userService.getUserByName(user.getUsername(), user.getPassword());
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		user = (User) resultMsg.getMsgEntity();
		if (user.getIschecked() == 0) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("该用户还未通过审核，请联系管理员后重试！");
			return jsonPackage;
		}
		if (user.getIschecked() == 2) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("该用户已被锁定，请联系管理员后重试！");
			return jsonPackage;
		}
		
		// 把用户信息放入session中
		SessionObject sessionObject = getSessionAttribute();
		sessionObject.setUser(user);
		setSessionAttribute(sessionObject);
		String url;
		if (user.getUsertype() == 0) {
			// 游客登录，跳转主页
			url = getUrl_BizFunc("", "");
			jsonPackage.setResult(url);
			return jsonPackage;
		}
		if (user.getUsertype() == 1) {
			// 旅行社登录，跳转到基础信息管理页面（车辆管理）
			url = getUrl_BizFunc("", "");
			jsonPackage.setResult(url);
			return jsonPackage;
		}
		if (user.getUsertype() == 2) {
			// 网站工作人员登录，跳转到基础信息管理页面（车票管理）
			url = getUrl_BizFunc("staff", "index.html");
			jsonPackage.setResult(url);
			return jsonPackage;
		}
		if (user.getUsertype() == 3) {
			// 管理员登录，跳转到用户信息管理页面
			url = getUrl_BizFunc("admin", "index.html");
			jsonPackage.setResult(url);
			return jsonPackage;
		}
		return jsonPackage;
	}
	
	@RequestMapping(value = "/test.html")
    public JsonPackage test(HttpServletRequest request, HttpServletResponse response){
		JsonPackage jsonPackage = new JsonPackage();
        return jsonPackage;
    }
}
