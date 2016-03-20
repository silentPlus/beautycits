package com.zyh.beautycits.service.ticket.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.ticket.TicketService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.ticket.Ticket;

@Service("ticketService")
public class TicketServiceImpl extends BaseServiceImpl implements TicketService{
	
	@Autowired
	private JdbcBaseDao<Ticket> ticketDao;

	@Override
	public ResultMsg getTickets(Integer currentPage, String name, Integer star, Integer tickettypeid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select t.*, tt.`name` as tickettype from ticket t, tickettype tt where tt.id = t.tickettypeid ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and t.name like '%").append(name).append("%' ");
		}
		if (star != null) {
			sql.append(" and t.star = ").append(star);
		}
		if (tickettypeid != null) {
			sql.append(" and t.tickettypeid = ").append(tickettypeid);
		}
		
		PageInfo<Ticket> pageTicket = new PageInfo<>();
		pageTicket.setPageSize(2);
		pageTicket.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageTicket = ticketDao.getPageModel(pageTicket, sql, countsql, Ticket.class);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageTicket);
		return resultMsg;
	}

	@Override
	public ResultMsg addTicket(Ticket ticket) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT INTO ticket(provinceid, cityid, areaid, area, name, star, cost, tickettypeid, description, remark, createtime)");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,now())");
		sql.trimToSize();
		int num = ticketDao.commonUpdate(sql.toString(), ticket.getProvinceid(), ticket.getCityid(), ticket.getAreaid(), ticket.getArea(), ticket.getName(),
				ticket.getStar(), ticket.getCost(), ticket.getTickettypeid(), ticket.getDescription(), ticket.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteTicket(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from ticket where id = ?";
		int num = ticketDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
