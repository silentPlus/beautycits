package com.zyh.beautycits.service.line;

import java.util.List;
import java.util.Map;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.line.LineShow;
import com.zyh.beautycits.vo.line.Schedule;
import com.zyh.beautycits.vo.line.ScheduleTicket;

public interface LineShowService extends BaseService{
	/**
	 * 
	 * getLinesShow(获取线路信息)
	 * @date 2016年3月23日 上午10:21:21
	 */
	public ResultMsg getLinesShow(Integer currentPage, String name, Integer lineTypeid);
	
	/**
	 * 
	 * getLineDetail(获取线路详细信息)
	 */
	public LineShow getLineDetail(Integer linedetailid);
	
	/**
	 * 
	 * getSchedule(获取线路日程安排)
	 */
	public List<Schedule> getSchedule(Integer linedetailid);
	
	/**
	 * 
	 * getTicket(获取日程门票信息)
	 */
	public Map<Integer, List<ScheduleTicket>> getTicket(Integer linedetailid);
}
