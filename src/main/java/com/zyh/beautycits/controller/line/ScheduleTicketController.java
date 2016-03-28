package com.zyh.beautycits.controller.line;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.line.ScheduleTicketService;
import com.zyh.beautycits.service.ticket.TicketService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.ScheduleTicket;
import com.zyh.beautycits.vo.ticket.Ticket;

@RestController
@RequestMapping("scheduleticket")
public class ScheduleTicketController extends BaseController{
	
	@Autowired
	private ScheduleTicketService scheduleTicketService;
	
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(value = "/index.html")
    public JsonPackage index(HttpServletRequest request, HttpServletResponse response, Integer scheduleid) throws Exception {
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		
		// 获取scheduleid对应的门票信息
		ResultMsg resultMsg = scheduleTicketService.getScheduleTickets(scheduleid);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		List<Ticket> listTicket = ticketService.getAllTicket();
		jsonPackage.setResult(resultMsg.getMsgEntity());
		jsonPackage.put("listTicket", listTicket);
		return jsonPackage;
    }
	
	@RequestMapping(value = "/addscheduleticket.html")
    public JsonPackage addScheduleTicket(HttpServletRequest request, HttpServletResponse response, ScheduleTicket scheduleTicket){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = scheduleTicketService.saveScheduleTicket(scheduleTicket);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/deletescheduleticket.html")
    public JsonPackage deleteSchedule(HttpServletRequest request, HttpServletResponse response,Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = scheduleTicketService.deleteScheduleTicket(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
		}
		return jsonPackage;
    }
}
