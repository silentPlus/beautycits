package com.zyh.beautycits.service.staff;

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
	public ResultMsg getVehicle(Integer vehicletype, Integer originid, Integer destinationid);
	
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
	 * getVehicleById(根据主键查询交通信息)
	 *
	 * @param id
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg getVehicleById(Integer id);
	
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
	 * updateVehicle(更新)
	 *
	 * @param vehicle
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg updateVehicle(Vehicle vehicle);
}
