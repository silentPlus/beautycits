package com.zyh.beautycits.controller.staff;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.region.RegionService;
import com.zyh.beautycits.service.staff.VehicleService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.region.Area;
import com.zyh.beautycits.vo.region.City;
import com.zyh.beautycits.vo.region.Province;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("staff")
public class StaffController extends BaseController{
	
	@Autowired
	private VehicleService vehicleService;	
	
	@Autowired
	private RegionService regionService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		// 判断是否登录
		if (!isLogin()) {
			String url_login = getUrl_BizFunc("login", "index.html");
			mav.setViewName("redirect:"+url_login);
			return mav;
		}
		// 获取所有交通信息
		ResultMsg resultMsg = vehicleService.getVehicle(1, null, null, null, null, null, null, null);
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("vehicles", JSON.toJSONString(resultMsg.getMsgEntity()));
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		
		List<Province> provinces = regionService.getProvinces();
		JSONObject citys = regionService.getcitys();
		JSONObject areas = regionService.getareas();
		mav.addObject("provinces", JSON.toJSONString(provinces));
		mav.addObject("citys", citys);
		mav.addObject("areas", areas);
		
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.setViewName("/staff");
        return mav;
    }
}
