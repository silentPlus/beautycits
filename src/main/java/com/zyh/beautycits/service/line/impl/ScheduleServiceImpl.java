package com.zyh.beautycits.service.line.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.ScheduleService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.Schedule;
import com.zyh.beautycits.vo.line.ScheduleTicket;
import com.zyh.beautycits.vo.quote.InnerQuote;

@Service("scheduleService")
public class ScheduleServiceImpl extends BaseServiceImpl implements ScheduleService {
	
	@Autowired
	private JdbcBaseDao<Schedule> scheduleDao;
	
	@Autowired
	private JdbcBaseDao<InnerQuote> innerQuoteDao;
	
	@Autowired
	private JdbcBaseDao<ScheduleTicket> scheduleTicketDao;

	@Override
	public ResultMsg getSchedules(Integer linedetailid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("select s.*, ht.`name` as hotel, b.`name` as bus, rt1.`name` as morestaurant, rt2.`name` as lurestaurant, rt3.`name` as direstaurant ");
		sql.append("from `schedule` s ");
		sql.append("LEFT JOIN (hotel h LEFT JOIN hoteltype ht on ht.id = h.hoteltypeid ) on h.id = s.hotelid ");
		sql.append("LEFT JOIN bus b on b.id = s.busid ");
		sql.append("LEFT JOIN (restaurant mr LEFT JOIN restauranttype rt1 on rt1.id = mr.restauranttypeid) on mr.id = s.morestaurantid ");
		sql.append("LEFT JOIN (restaurant lr LEFT JOIN restauranttype rt2 on rt2.id = lr.restauranttypeid) on lr.id = s.lurestaurantid ");
		sql.append("LEFT JOIN (restaurant dr LEFT JOIN restauranttype rt3 on rt3.id = dr.restauranttypeid) on dr.id = s.direstaurantid ");
		sql.append("where s.linedetailid = ? ORDER BY s.day asc");
		List<Schedule> list = scheduleDao.getList(sql.toString(), Schedule.class, linedetailid);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(list);
		return resultMsg;
	}

	@Override
	public ResultMsg saveSchedule(Schedule schedule) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("insert into `schedule`(linedetailid, day, hotelid, busid, morestaurantid, lurestaurantid, direstaurantid,  ");
		sql.append("hotelcost, buscost, mocost, lucost, dicost, remark, createtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,now())");
		sql.trimToSize();
		int num = scheduleDao.commonUpdate(sql.toString(), schedule.getLinedetailid(), schedule.getDay(), schedule.getHotelid(), schedule.getBusid(), 
				schedule.getMorestaurantid(), schedule.getLurestaurantid(), schedule.getDirestaurantid(), schedule.getHotelcost(), schedule.getBuscost(), 
				schedule.getMocost(), schedule.getLucost(), schedule.getDicost(), schedule.getRemark());
		if (num == 1) {
			// 更新对内报价的金额
			BigDecimal hotelcost = new BigDecimal(StringUtils.isBlank(schedule.getHotelcost())?"0":schedule.getHotelcost());
			BigDecimal buscost = new BigDecimal(StringUtils.isBlank(schedule.getBuscost())?"0":schedule.getBuscost());
			BigDecimal mocost = new BigDecimal(StringUtils.isBlank(schedule.getMocost())?"0":schedule.getMocost());
			BigDecimal lucost = new BigDecimal(StringUtils.isBlank(schedule.getLucost())?"0":schedule.getLucost());
			BigDecimal dicost = new BigDecimal(StringUtils.isBlank(schedule.getDicost())?"0":schedule.getDicost());
			Integer linedetailid = schedule.getLinedetailid();
			String ssql = "select * from innerquote where linedetailid = ?";
			InnerQuote innerQuote = innerQuoteDao.getJavaBean(ssql, InnerQuote.class, linedetailid);
			BigDecimal cost = new BigDecimal(innerQuote.getPrimecost()).add(hotelcost).add(buscost).add(mocost).add(lucost).add(dicost);
			ssql = "update innerquote set primecost = ?, updatetime = now() where linedetailid = ?";
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
	public ResultMsg deleteSchedule(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		// 1.删除对内报价的门票价格和日程安排中的其他费用
		String sql = "select * from scheduleticket where scheduleid = ?";
		List<ScheduleTicket> list = scheduleTicketDao.getList(sql, ScheduleTicket.class, id);
		
		sql = "select * from schedule s LEFT JOIN linedetail ld LEFT JOIN innerquote i on i.linedetailid = ld.id on ld.id = s.linedetailid where s.id = ?";
		Map<String, Object> map = scheduleTicketDao.getMap(sql, id);
		Integer linedetailid = Integer.valueOf(map.get("linedetailid").toString());
		BigDecimal cost = new BigDecimal(map.get("primecost").toString());
		
		for (ScheduleTicket scheduleTicket : list) {
			BigDecimal ticketcost = new BigDecimal(StringUtils.isBlank(scheduleTicket.getTicketcost().toString())?"0":scheduleTicket.getTicketcost().toString());
			cost = cost.subtract(ticketcost);
		}
		BigDecimal hotelcost = new BigDecimal(StringUtils.isBlank(map.get("hotelcost").toString())?"0":map.get("hotelcost").toString());
		BigDecimal buscost = new BigDecimal(StringUtils.isBlank(map.get("buscost").toString())?"0":map.get("buscost").toString());
		BigDecimal mocost = new BigDecimal(StringUtils.isBlank(map.get("mocost").toString())?"0":map.get("mocost").toString());
		BigDecimal lucost = new BigDecimal(StringUtils.isBlank(map.get("lucost").toString())?"0":map.get("lucost").toString());
		BigDecimal dicost = new BigDecimal(StringUtils.isBlank(map.get("dicost").toString())?"0":map.get("dicost").toString());
		cost = cost.subtract(hotelcost).subtract(buscost).subtract(mocost).subtract(lucost).subtract(dicost);
		sql = "update innerquote set primecost = ?, updatetime = now() where linedetailid = ?";
		int num = innerQuoteDao.commonUpdate(sql, cost.toString(), linedetailid);
		if (num != 1) {
			logger.error("更新对内报价失败！sql:" + sql + ", linedetailid:" + linedetailid + ", cost:" + cost);
		}
		
		// 2.删除日程安排包含的门票信息
		sql = "delete from scheduleticket where scheduleid = ?";
		num = scheduleDao.commonUpdate(sql, id);
		
		// 3.再删除日程安排信息
		sql = "delete from `schedule` where id = ?";
		num = scheduleDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
