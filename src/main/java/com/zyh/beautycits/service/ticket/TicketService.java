package com.zyh.beautycits.service.ticket;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.ticket.Ticket;

public interface TicketService extends BaseService{
	/**
	 * 
	 * getVehicle(查询所有门票信息)
	 */
	public ResultMsg getTickets(Integer currentPage, String name, Integer star, Integer tickettype);
	
	/**
	 * 
	 * addTicketType(新增门票)
	 * @date 2016年3月19日 下午2:09:43
	 *
	 */
	public ResultMsg addTicket(Ticket ticket);
	
	/**
	 * 
	 * deleteTicketType(删除门票)
	 * @date 2016年3月19日 下午2:10:06
	 */
	public ResultMsg deleteTicket(Integer id);
}
