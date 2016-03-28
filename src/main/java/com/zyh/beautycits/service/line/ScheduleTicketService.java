package com.zyh.beautycits.service.line;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.line.ScheduleTicket;

public interface ScheduleTicketService extends BaseService {
	/**
	 * 
	 * getScheduleTickets(获取日程安排的门票信息)
	 */
	public ResultMsg getScheduleTickets(Integer scheduleid);
	
	/**
	 * 
	 * saveScheduleTicket(保存日程安排的门票信息)
	 */
	public ResultMsg saveScheduleTicket(ScheduleTicket scheduleTicket);
	
	/**
	 * 
	 * deleteScheduleTicket(删除日程安排的门票信息)
	 */
	public ResultMsg deleteScheduleTicket(Integer id);
}
