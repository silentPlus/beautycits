package com.zyh.beautycits.controller.travel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.line.LineShowService;
import com.zyh.beautycits.service.travel.TravelUserService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.LineShow;
import com.zyh.beautycits.vo.travel.TravelUser;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("traveluser")
public class TravelUserController extends BaseController{
	
	@Autowired
	private LineShowService lineShowService;
	
	@Autowired
	private TravelUserService travelUserService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Integer linedetailid) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 判断是否登录
		if (!isLogin()) {
			String url_login = getUrl_BizFunc("login", "index.html");
			mav.setViewName("redirect:"+url_login);
			return mav;
		}
				
		// 获取线路信息
		LineShow lineShow = lineShowService.getLineDetail(linedetailid);
		mav.addObject("lineShow", lineShow);
		
		User user = getSessionUser();
		ResultMsg resultMsg = travelUserService.getTravelUsers(linedetailid, user.getId());
		mav.addObject("listTravelUser", JSON.toJSONString(resultMsg.getMsgEntity()));
		
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		String url_dingdan = getUrl_BizFunc("travelquote", "index.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.addObject("url_dingdan", url_dingdan);
		mav.setViewName("/travelquote");
        return mav;
    }
	
	@RequestMapping(value = "/addtraveluser.html")
    public JsonPackage addTravelUser(HttpServletRequest request, HttpServletResponse response, 
    		TravelUser travelUser){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		User user = getSessionUser();
		ResultMsg resultMsg = travelUserService.addTravelUser(travelUser, user.getId());
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/deletetravleuser.html")
    public JsonPackage delTravleUser(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = travelUserService.delTravelUser(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
}
