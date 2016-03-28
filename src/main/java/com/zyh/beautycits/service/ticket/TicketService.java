package com.zyh.beautycits.service.ticket;

import java.util.List;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.ticket.Ticket;

public interface TicketService extends BaseService{
	/**
	 * 
	 * getTickets(查询所有门票信息)
	 */
	public ResultMsg getTickets(Integer currentPage, String name, Integer star, Integer tickettype);
	
	/**
	 * 
	 * addTicket(新增门票)
	 * @date 2016年3月19日 下午2:09:43
	 *
	 */
	public ResultMsg addTicket(Ticket ticket);
	
	/**
	 * 
	 * deleteTicket(删除门票)
	 * @date 2016年3月19日 下午2:10:06
	 */
	public ResultMsg deleteTicket(Integer id);
	
	/**
	 * 
	 * getAllTicket(获取所有门票信息)
	 */
	public List<Ticket> getAllTicket();
}
