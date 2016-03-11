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
		// 获取所有公司内部人员信息
		ResultMsg resultMsg = userService.getUsersByType(2);
		if (resultMsg.getState() == Results.ERROR) {
			goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("users", JSON.toJSONString(resultMsg.getMsgEntity()));
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.setViewName("/admin_users");
        return mav;
    }
	
	@RequestMapping(value = "/edituser.html")
    public ModelAndView edituser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 判断是否登录
		if (!isLogin()) {
			String url_login = getUrl_BizFunc("login", "index.html");
			mav.setViewName("redirect:"+url_login);
			return mav;
		}
		mav.setViewName("user_edit");
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "deituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		return mav;
    }
	
	@RequestMapping(value = "/lock.html")
    public JsonPackage lock(HttpServletRequest request, HttpServletResponse response,
    		Integer id, Integer ischecked){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = userService.lockUser(id, ischecked == 1? 2 : 1);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
	
	@RequestMapping(value = "/check.html")
    public JsonPackage check(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = userService.checkUser(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
	
	@RequestMapping(value = "/delete.html")
    public JsonPackage delete(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = userService.deleteUser(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
	
	@RequestMapping(value = "/gettypeusers.html")
    public JsonPackage getTypeUsers(HttpServletRequest request, HttpServletResponse response,Integer type){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = userService.getUsersByType(type);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/saveuser.html")
    public JsonPackage saveUser(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = userService.saveUser(user);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		return jsonPackage;
    }
	
	@RequestMapping(value = "/adduser.html")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 判断是否登录
		if (!isLogin()) {
			String url_login = getUrl_BizFunc("login", "index.html");
			mav.setViewName("redirect:"+url_login);
			return mav;
		}
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "deituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.setViewName("user_add");
		return mav;
    }
	
	@RequestMapping(value = "/checkusername.html")
    public JsonPackage checkUserName(HttpServletRequest request, HttpServletResponse response, String username, Integer usertype){
		JsonPackage jsonPackage = new JsonPackage();
		ResultMsg resultMsg = userService.checkUserName(username, usertype);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
		}
		return jsonPackage;
    }
	
	@RequestMapping(value = "/doedit.html")
    public JsonPackage doEdit(HttpServletRequest request, HttpServletResponse response, User user){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		// 获取登录用户信息
		User uuser = getSessionUser();
		ResultMsg resultMsg = userService.updateUser(user, uuser.getId());
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
	
	@RequestMapping(value = "/changepwd.html")
    public JsonPackage changePwd(HttpServletRequest request, HttpServletResponse response, String password, String newpassword){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}// 获取登录用户信息
		User user = getSessionUser();
		ResultMsg resultMsg = userService.changePwd(user.getId(), password, newpassword);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
	
}
