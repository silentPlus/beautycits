package com.zyh.beautycits.controller.hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.hotel.HotelTypeService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.hotel.HotelType;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("hoteltype")
public class HotelTypeController extends BaseController {
	
	@Autowired
	private HotelTypeService hotelTypeService;
	
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
		// 获取所有门票类别信息
		ResultMsg resultMsg = hotelTypeService.getHotelTypes(1, null, user.getId());
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("pageHotelTypes", JSON.toJSONString(resultMsg.getMsgEntity()));
		
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.setViewName("/hotel_type");
        return mav;
    }
	
	@RequestMapping(value = "/searchtype.html")
    public JsonPackage searchType(HttpServletRequest request, HttpServletResponse response, Integer currentPage, String name){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		// 获取登录用户信息
		User user = getSessionUser();
		ResultMsg resultMsg =  hotelTypeService.getHotelTypes(currentPage, name, user.getId());
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/addhoteltype.html")
    public JsonPackage addHotelType(HttpServletRequest request, HttpServletResponse response, HotelType hotelType){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = hotelTypeService.addHotelType(hotelType);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/deletehoteltype.html")
    public JsonPackage deleteHotelType(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = hotelTypeService.deleteHotelType(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
	
}
