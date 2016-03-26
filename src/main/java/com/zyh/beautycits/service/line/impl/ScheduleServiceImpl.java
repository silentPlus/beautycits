package com.zyh.beautycits.service.line.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.ScheduleService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.Schedule;

public class ScheduleServiceImpl extends BaseServiceImpl implements ScheduleService {
	
	@Autowired
	private JdbcBaseDao<Schedule> scheduleDao;

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
		String sql = "delete from `schedule` where id = ?";
		int num = scheduleDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
