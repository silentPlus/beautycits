package com.zyh.beautycits.service.ticket;

import java.util.List;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.ticket.TicketType;

public interface TicketTypeService extends BaseService{
	/**
	 * 
	 * getVehicle(查询所有门票类别信息)
	 */
	public ResultMsg getTicketTypes(Integer currentPage, String name);
	
	/**
	 * 
	 * addTicketType(新增门票类别)
	 * @date 2016年3月19日 下午2:09:43
	 *
	 */
	public ResultMsg addTicketType(TicketType ticketType);
	
	/**
	 * 
	 * deleteTicketType(删除门票类别)
	 * @date 2016年3月19日 下午2:10:06
	 */
	public ResultMsg deleteTicketType(Integer id);
	
	/**
	 * 
	 * getAllTicketType(获取全部门票类型信息)
	 * @date 2016年3月19日 下午3:42:26
	 */
	public List<TicketType> getAllTicketType();
}
