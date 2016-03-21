package com.zyh.beautycits.controller.restaurant;

import java.util.List;

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
import com.zyh.beautycits.service.restaurant.RestaurantService;
import com.zyh.beautycits.service.restaurant.RestaurantTypeService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.region.Province;
import com.zyh.beautycits.vo.restaurant.Restaurant;
import com.zyh.beautycits.vo.restaurant.RestaurantType;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("restaurant")
public class RestaurantController extends BaseController{
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestaurantTypeService restaurantTypeService;
	
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
		
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		
		// 获取所有宾馆信息
		ResultMsg resultMsg = restaurantService.getRestaurants(1, null, null, user.getId());
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("restaurantPage", JSON.toJSONString(resultMsg.getMsgEntity()));
		
		List<RestaurantType> listType = restaurantTypeService.getAllRestaurantType(user.getId());
		mav.addObject("listType", JSON.toJSONString(listType));
		
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
		mav.setViewName("/restaurant");
        return mav;
    }
	
	@RequestMapping(value = "/search.html")
    public JsonPackage search(HttpServletRequest request, HttpServletResponse response, Integer currentPage, 
    		Integer format, Integer restauranttypeid){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		// 获取登录用户信息
		User user = getSessionUser();
		ResultMsg resultMsg =  restaurantService.getRestaurants(currentPage, restauranttypeid, format, user.getId());
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/addrestaurant.html")
    public JsonPackage addRestaurant(HttpServletRequest request, HttpServletResponse response, Restaurant restaurant){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		// 获取登录用户信息
		User user = getSessionUser();
		restaurant.setUserid(user.getId());
		ResultMsg resultMsg = restaurantService.addRestaurant(restaurant);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/deleterestaurant.html")
    public JsonPackage deleteRestaurant(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = restaurantService.deleteRestaurant(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
}
