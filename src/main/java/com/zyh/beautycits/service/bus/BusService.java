package com.zyh.beautycits.service.bus;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.bus.Bus;

public interface BusService extends BaseService{
	/**
	 * 
	 * getBus(查询所有车辆信息)
	 *
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg getBus(Integer currentPage, Integer bustype, Integer isused, Integer userid);
	
	/**
	 * 
	 * saveBus(保存车辆信息)
	 *
	 * @param vehicle
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg saveBus(Bus bus);
	
	/**
	 * 
	 * deleteBus(删除)
	 *
	 * @param id
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg deleteBus(Integer id);
}
