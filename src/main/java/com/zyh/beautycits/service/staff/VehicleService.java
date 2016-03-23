package com.zyh.beautycits.service.staff;

import java.util.List;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.vehicle.Vehicle;

public interface VehicleService extends BaseService{
	
	/**
	 * 
	 * getVehicle(查询所有交通信息)
	 *
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg getVehicle(Integer currentPage, Integer vehicletype, Integer oareaid, Integer ocityid, Integer oprovinceid, 
			Integer dareaid, Integer dcityid, Integer dprovinceid);
	
	/**
	 * 
	 * saveVehicle(保存交通信息)
	 *
	 * @param vehicle
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg saveVehicle(Vehicle vehicle);
	
	/**
	 * 
	 * deleteVehicle(删除)
	 *
	 * @param id
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg deleteVehicle(Integer id);
	
	/**
	 * 
	 * getAllVehicle(获取所有交通信息)
	 */
	public List<Vehicle> getAllVehicle();
	
}
