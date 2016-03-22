package com.zyh.beautycits.controller.guide;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.guide.GuideService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.guide.Guide;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("guide")
public class GuideController extends BaseController{
	
	@Autowired
	private GuideService guideService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)  throws Exception {
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
		
		// 获取所有车辆信息
		ResultMsg resultMsg = guideService.getGuide(1, null, null, user.getId());
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("guidesPage", JSON.toJSONString(resultMsg.getMsgEntity()));
		
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.setViewName("/guide");
        return mav;
    }
	
	@RequestMapping(value = "/search.html")
    public JsonPackage search(HttpServletRequest request, HttpServletResponse response, Integer currentPage, 
    		String guidename, Integer isused){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		// 获取登录用户信息
		User user = getSessionUser();
		ResultMsg resultMsg =  guideService.getGuide(currentPage, guidename, isused, user.getId());
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/addguide.html")
    public JsonPackage addGuide(HttpServletRequest request, HttpServletResponse response, Guide guide){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		// 获取登录用户信息
		User user = getSessionUser();
		guide.setUserid(user.getId());
		ResultMsg resultMsg = guideService.saveGuide(guide);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/deleteguide.html")
    public JsonPackage deleteGuide(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = guideService.deleteGuide(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
}
