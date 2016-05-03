package com.zyh.beautycits.service.line.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.LineShowService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.LineShow;
import com.zyh.beautycits.vo.line.Schedule;
import com.zyh.beautycits.vo.line.ScheduleTicket;

@Service("lineShowService")
public class LineShowServiceImpl extends BaseServiceImpl implements LineShowService{

	@Autowired
	private JdbcBaseDao<LineShow> lineShowDao;
	
	@Autowired
	private JdbcBaseDao<ScheduleTicket> scheduleTicketDao;
	
	@Autowired
	private JdbcBaseDao<Schedule> scheduleDao;
	
	@Override
	public ResultMsg getLinesShow(Integer currentPage, String name, Integer lineTypeid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select ld.id as id, l.`name` as linename, l.`day` as day, lt.`name` as linetype, gv.vehicletype as govehicleid, bv.vehicletype as backvehicleid, l.updatetime as publishtime from linedetail ld ");
		sql.append("LEFT JOIN line l LEFT JOIN linetype lt on lt.id = l.linetypeid on l.id = ld.lineid ");
		sql.append("LEFT JOIN vehicle gv on gv.id = ld.govehicleid ");
		sql.append("LEFT JOIN vehicle bv on bv.id = ld.backvehicleid ");
		sql.append("where l.deleteflg = 0 and l.ispublish = 1 ");
		if (StringUtils.isNotBlank(name)) {
			sql.append("and l.name like '%").append(name).append("%' ");
		}
		if (lineTypeid != null) {
			sql.append("and l.linetypeid = ").append(lineTypeid);
		}
		
		PageInfo<LineShow> pageLineShow = new PageInfo<>();
		pageLineShow.setPageSize(ConfigConstants.PAGESIZE);
		pageLineShow.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageLineShow = lineShowDao.getPageModel(pageLineShow, sql, countsql, LineShow.class);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageLineShow);
		return resultMsg;
	}

	@Override
	public LineShow getLineDetail(Integer linedetailid) {
		StringBuffer sql = new StringBuffer("select ld.id as id, l.`name` as linename, l.`day` as day, lt.`name` as linetype, gv.vehicletype as govehicleid, bv.vehicletype as backvehicleid, l.updatetime as publishtime, ld.number from linedetail ld ");
		sql.append("LEFT JOIN line l LEFT JOIN linetype lt on lt.id = l.linetypeid on l.id = ld.lineid ");
		sql.append("LEFT JOIN vehicle gv on gv.id = ld.govehicleid ");
		sql.append("LEFT JOIN vehicle bv on bv.id = ld.backvehicleid ");
		sql.append("where ld.id=? ");
		LineShow lineShow = lineShowDao.getJavaBean(sql.toString(), LineShow.class, linedetailid);
		return lineShow;
	}

	@Override
	public List<Schedule> getSchedule(Integer linedetailid) {
		StringBuilder sql = new StringBuilder("select s.*, ht.`name` as hotel, b.`name` as bus, rt1.`name` as morestaurant, rt2.`name` as lurestaurant, rt3.`name` as direstaurant ");
		sql.append("from `schedule` s ");
		sql.append("LEFT JOIN (hotel h LEFT JOIN hoteltype ht on ht.id = h.hoteltypeid ) on h.id = s.hotelid ");
		sql.append("LEFT JOIN bus b on b.id = s.busid ");
		sql.append("LEFT JOIN (restaurant mr LEFT JOIN restauranttype rt1 on rt1.id = mr.restauranttypeid) on mr.id = s.morestaurantid ");
		sql.append("LEFT JOIN (restaurant lr LEFT JOIN restauranttype rt2 on rt2.id = lr.restauranttypeid) on lr.id = s.lurestaurantid ");
		sql.append("LEFT JOIN (restaurant dr LEFT JOIN restauranttype rt3 on rt3.id = dr.restauranttypeid) on dr.id = s.direstaurantid ");
		sql.append("where s.linedetailid = ? ORDER BY s.day asc");
		List<Schedule> list = scheduleDao.getList(sql.toString(), Schedule.class, linedetailid);
		return list;
	}

	@Override
	public Map<Integer, List<ScheduleTicket>> getTicket(Integer linedetailid) {
		StringBuilder sql = new StringBuilder("select st.*, t.name as ticket from ");
		sql.append("scheduleticket st LEFT JOIN ticket t on t.id = st.ticketid ");
		sql.append("LEFT JOIN `schedule` s on s.id = st.scheduleid ");
		sql.append("where s.linedetailid = ? ORDER BY st.createtime asc ");
		List<ScheduleTicket> list = scheduleTicketDao.getList(sql.toString(), ScheduleTicket.class, linedetailid);
		Map<Integer, List<ScheduleTicket>> map = new HashMap<Integer, List<ScheduleTicket>>();
		for (ScheduleTicket scheduleTicket : list) {
			if (map.containsKey(scheduleTicket.getScheduleid())) {
				List<ScheduleTicket> ll = map.get(scheduleTicket.getScheduleid());
				ll.add(scheduleTicket);
				map.put(scheduleTicket.getScheduleid(), ll);
			} else {
				List<ScheduleTicket> ll = new ArrayList<ScheduleTicket>();
				ll.add(scheduleTicket);
				map.put(scheduleTicket.getScheduleid(), ll);
			}
		}
		return map;
	}

}
