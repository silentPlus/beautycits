package com.zyh.beautycits.service.line;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.line.Schedule;

public interface ScheduleService extends BaseService {
	
	/**
	 * 
	 * getSchedules(获取旅行社负责线路日程安排信息)
	 */
	public ResultMsg getSchedules(Integer linedetailid);
	
	/**
	 * 
	 * saveSchedule(保存日程信息)
	 */
	public ResultMsg saveSchedule(Schedule schedule);
	
	/**
	 * 
	 * deleteSchedule(删除日程线路)
	 */
	public ResultMsg deleteSchedule(Integer id);

}
