package com.zyh.beautycits.controller.ticket;

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
import com.zyh.beautycits.service.ticket.TicketService;
import com.zyh.beautycits.service.ticket.TicketTypeService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.region.Province;
import com.zyh.beautycits.vo.ticket.Ticket;
import com.zyh.beautycits.vo.ticket.TicketType;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("ticket")
public class TicketController extends BaseController{
	
	@Autowired
	private TicketService ticketService;
	@Autowired
	private TicketTypeService ticketTypeService;
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
		// 获取所有门票信息
		ResultMsg resultMsg = ticketService.getTickets(1, null, null, null);
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("ticketsPage", JSON.toJSONString(resultMsg.getMsgEntity()));
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		
		List<TicketType> listType = ticketTypeService.getAllTicketType();
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
		mav.setViewName("/ticket");
        return mav;
    }
	
	@RequestMapping(value = "/search.html")
    public JsonPackage searchType(HttpServletRequest request, HttpServletResponse response, Integer currentPage, String name,
    		Integer star, Integer tickettype){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg =  ticketService.getTickets(currentPage, name, star, tickettype);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/addticket.html")
    public JsonPackage addTicket(HttpServletRequest request, HttpServletResponse response, Ticket ticket){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = ticketService.addTicket(ticket);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/deleteticket.html")
    public JsonPackage deleteTicket(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = ticketService.deleteTicket(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
}
