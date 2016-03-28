package com.zyh.beautycits.service.line.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.ScheduleTicketService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.ScheduleTicket;

@Service("scheduleTicketService")
public class ScheduleTicketServiceImpl extends BaseServiceImpl implements ScheduleTicketService {

	@Autowired
	private JdbcBaseDao<ScheduleTicket> scheduleTicketDao;
	
	@Override
	public ResultMsg getScheduleTickets(Integer scheduleid) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select st.*, t.name as ticket from scheduleticket st LEFT JOIN ticket t on t.id = st.ticketid where st.scheduleid = ? ORDER BY st.createtime asc";
		List<ScheduleTicket> list = scheduleTicketDao.getList(sql.toString(), ScheduleTicket.class, scheduleid);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(list);
		return resultMsg;
	}

	@Override
	public ResultMsg saveScheduleTicket(ScheduleTicket scheduleTicket) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT into scheduleticket(scheduleid, ticketid, ticketcost, createtime) values(?,?,?,now())");
		sql.trimToSize();
		int num = scheduleTicketDao.commonUpdate(sql.toString(), scheduleTicket.getScheduleid(), scheduleTicket.getTicketid(), scheduleTicket.getTicketcost());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteScheduleTicket(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from scheduleticket where id = ?";
		int num = scheduleTicketDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
