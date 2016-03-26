package com.zyh.beautycits.controller.line;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.bus.BusService;
import com.zyh.beautycits.service.hotel.HotelService;
import com.zyh.beautycits.service.line.LineDetailService;
import com.zyh.beautycits.service.line.ScheduleService;
import com.zyh.beautycits.service.restaurant.RestaurantService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.bus.Bus;
import com.zyh.beautycits.vo.hotel.Hotel;
import com.zyh.beautycits.vo.line.Schedule;
import com.zyh.beautycits.vo.restaurant.Restaurant;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("schedule")
public class ScheduleController extends BaseController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private LineDetailService lineDetailService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Integer linedetailid) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 判断是否登录
		if (!isLogin() && linedetailid != null) {
			String url_login = getUrl_BizFunc("login", "index.html");
			mav.setViewName("redirect:"+url_login);
			return mav;
		}
		
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		
		// 获取linedetailid对应的线路信息（名称和天数）
		Map<String, Object> map = lineDetailService.getLineSchedul(linedetailid);
		mav.addObject("linename", map.get("linename"));
		mav.addObject("day", map.get("day"));
		
		// 获取linedetailid对应的日程安排信息
		ResultMsg resultMsg = scheduleService.getSchedules(linedetailid);
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("schedules", JSON.toJSONString(resultMsg.getMsgEntity()));
		
		Integer userid = user.getId();
		List<Hotel> listHotel = hotelService.getAllHotel(userid);
		mav.addObject("listHotel", JSON.toJSONString(listHotel));
		List<Bus> listBus = busService.getAllBus(userid);
		mav.addObject("listBus", JSON.toJSONString(listBus));
		List<Restaurant> listRestaurant = restaurantService.getAllRestaurant(userid);
		mav.addObject("listRestaurant", JSON.toJSONString(listRestaurant));
		
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.addObject("linedetailid", linedetailid);
		mav.setViewName("/schedule");
        return mav;
    }
	
	@RequestMapping(value = "/addschedule.html")
    public JsonPackage addSchedule(HttpServletRequest request, HttpServletResponse response, Schedule schedule){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = scheduleService.saveSchedule(schedule);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/deleteschedule.html")
    public JsonPackage deleteSchedule(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = scheduleService.deleteSchedule(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
}
