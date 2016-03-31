package com.zyh.beautycits.service.line.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.ScheduleTicketService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.ScheduleTicket;
import com.zyh.beautycits.vo.quote.InnerQuote;

@Service("scheduleTicketService")
public class ScheduleTicketServiceImpl extends BaseServiceImpl implements ScheduleTicketService {

	@Autowired
	private JdbcBaseDao<ScheduleTicket> scheduleTicketDao;
	
	@Autowired
	private JdbcBaseDao<InnerQuote> innerQuoteDao;
	
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
			// 更新对内报价的金额
			BigDecimal ticketcost = new BigDecimal(StringUtils.isBlank(scheduleTicket.getTicketcost())?"0":scheduleTicket.getTicketcost());
			Integer scheduleid = scheduleTicket.getScheduleid();
			String ssql = "select * from schedule s LEFT JOIN linedetail ld LEFT JOIN innerquote i on i.linedetailid = ld.id on ld.id = s.linedetailid where s.id = ?";
			Map<String, Object> map = scheduleTicketDao.getMap(ssql, scheduleid);
			Integer linedetailid = Integer.valueOf(map.get("linedetailid").toString());
			BigDecimal cost = new BigDecimal(map.get("primecost").toString()).add(ticketcost);
			ssql = "update innerquote set primecost = ? where linedetailid = ?";
			num = innerQuoteDao.commonUpdate(ssql, cost.toString(), linedetailid);
			if (num != 1) {
				logger.error("更新对内报价失败！sql:" + ssql + ", linedetailid:" + linedetailid + ", cost:" + cost);
			}
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
		// 先更新对内报价表信息
		String sql = "select * from scheduleticket st LEFT JOIN schedule s LEFT JOIN linedetail ld LEFT JOIN innerquote i on i.linedetailid = ld.id on ld.id = s.linedetailid  on s.id = st.scheduleid where st.id = ?";
		Map<String, Object> map = scheduleTicketDao.getMap(sql, id);
		BigDecimal ticketcost = new BigDecimal(StringUtils.isBlank(map.get("ticketcost").toString())?"0":map.get("ticketcost").toString());
		Integer linedetailid = Integer.valueOf(map.get("linedetailid").toString());
		BigDecimal cost = new BigDecimal(map.get("primecost").toString());
		cost = cost.subtract(ticketcost);
		sql = "update innerquote set primecost = ? where linedetailid = ?";
		int num = innerQuoteDao.commonUpdate(sql, cost.toString(), linedetailid);
		if (num != 1) {
			logger.error("更新对内报价失败！sql:" + sql + ", linedetailid:" + linedetailid + ", cost:" + cost);
		}
		
		sql = "delete from scheduleticket where id = ?";
		num = scheduleTicketDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
