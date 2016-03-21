package com.zyh.beautycits.service.ticket.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.ticket.TicketTypeService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.ticket.TicketType;

@Service("ticketTypeService")
public class TicketTypeServiceImpl extends BaseServiceImpl implements TicketTypeService{
	
	@Autowired
	private JdbcBaseDao<TicketType> ticketTypeDao;
	
	@Override
	public ResultMsg getTicketTypes(Integer currentPage, String name) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select * from tickettype tt ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" where tt.name like '%").append(name).append("%' ");
		}
		
		PageInfo<TicketType> pageTicketType = new PageInfo<>();
		pageTicketType.setPageSize(ConfigConstants.PAGESIZE);
		pageTicketType.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageTicketType = ticketTypeDao.getPageModel(pageTicketType, sql, countsql, TicketType.class);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageTicketType);
		return resultMsg;
	}

	@Override
	public ResultMsg addTicketType(TicketType ticketType) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT into tickettype(name, remark, createtime) values(?, ?, now())");
		sql.trimToSize();
		int num = ticketTypeDao.commonUpdate(sql.toString(), ticketType.getName(), ticketType.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteTicketType(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from tickettype where id = ?";
		int num = ticketTypeDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public List<TicketType> getAllTicketType() {
		String sql = "select * from tickettype";
		List<TicketType> list = ticketTypeDao.getList(sql, TicketType.class);
		return list;
	}

}
