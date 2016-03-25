package com.zyh.beautycits.controller.line;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.guide.GuideService;
import com.zyh.beautycits.service.line.LineDetailService;
import com.zyh.beautycits.service.line.LineService;
import com.zyh.beautycits.service.staff.VehicleService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.guide.Guide;
import com.zyh.beautycits.vo.line.Line;
import com.zyh.beautycits.vo.line.LineDetail;
import com.zyh.beautycits.vo.user.User;
import com.zyh.beautycits.vo.vehicle.Vehicle;

@RestController
@RequestMapping("linedetail")
public class LineDetailController extends BaseController {
	
	@Autowired
	private LineService lineService;
	
	@Autowired
	private LineDetailService lineDetailService;
	
	@Autowired
	private VehicleService vehicleService;
	
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
		
		// 获取所有旅行社参与线路信息
		ResultMsg resultMsg = lineDetailService.getLineDetails(1, user.getId(), null);
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("lineDetailsPage", JSON.toJSONString(resultMsg.getMsgEntity()));
		
		List<Line> listLine = lineService.getAllLine();
		mav.addObject("listLine", JSON.toJSONString(listLine));
		
		List<Vehicle> listVehicle = vehicleService.getAllVehicle();
		mav.addObject("listVehicle", JSON.toJSONString(listVehicle));
		
		List<Guide> listGuide = guideService.getAllGuide();
		mav.addObject("listGuide", JSON.toJSONString(listGuide));
		
		
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.setViewName("/line_detail");
        return mav;
    }
	
	@RequestMapping(value = "/search.html")
    public JsonPackage search(HttpServletRequest request, HttpServletResponse response, Integer currentPage, 
    		Integer lineid){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		
		User user = getSessionUser();
		ResultMsg resultMsg =  lineDetailService.getLineDetails(currentPage, user.getId(), lineid);
		
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/addlinedetail.html")
    public JsonPackage addLine(HttpServletRequest request, HttpServletResponse response, LineDetail lineDetail){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		User user = getSessionUser();
		lineDetail.setUserid(user.getId());
		ResultMsg resultMsg = lineDetailService.saveLineDetail(lineDetail);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/deletelinedetail.html")
    public JsonPackage deleteLineDetail(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = lineDetailService.deleteLineDetail(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }

	/*@RequestMapping(value = "/publishline.html")
    public JsonPackage publishLine(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = lineService.publishLine(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }*/
}
