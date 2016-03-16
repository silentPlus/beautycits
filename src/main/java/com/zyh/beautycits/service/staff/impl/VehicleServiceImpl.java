package com.zyh.beautycits.service.staff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.staff.VehicleService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.vehicle.Vehicle;

@Service("vehicleService")
public class VehicleServiceImpl extends BaseServiceImpl implements VehicleService{
	
	@Autowired
	private JdbcBaseDao<Vehicle> vehicleDao;
	
	@Override
	public ResultMsg getVehicle(Integer currentPage, Integer vehicletype, Integer oareaid, Integer ocityid, Integer oprovinceid, 
			Integer dareaid, Integer dcityid, Integer dprovinceid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("select * from vehicle v  ");
		if (vehicletype != null || oareaid != null || ocityid != null || oprovinceid != null || dareaid != null 
				|| dcityid != null || dprovinceid != null) {
			sql.append("where ");
		}
		if (vehicletype != null) {
			sql.append("v.vehicletype = ").append(vehicletype).append(" and ");
		}
		if (oareaid != null) {
			sql.append("v.oareaid = ").append(oareaid).append(" and ");
		}
		if (ocityid != null) {
			sql.append("v.ocityid = ").append(ocityid).append(" and ");
		}
		if (oprovinceid != null) {
			sql.append("v.oprovinceid = ").append(oprovinceid).append(" and ");
		}
		if (dareaid != null) {
			sql.append("v.dareaid = ").append(dareaid).append(" and ");
		}
		if (dcityid != null) {
			sql.append("v.dcityid = ").append(dcityid).append(" and ");
		}
		if (dprovinceid != null) {
			sql.append("v.dprovinceid = ").append(dprovinceid).append(" and ");
		}
		String ssql = sql.substring(0, sql.length()-4);
		
		PageInfo<Vehicle> pageVehicle = new PageInfo<>();
		pageVehicle.setPageSize(2);
		pageVehicle.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from vehicle");
		pageVehicle = vehicleDao.getPageModel(pageVehicle, new StringBuffer(ssql), countsql, Vehicle.class);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageVehicle);
		return resultMsg;
	}

	@Override
	public ResultMsg saveVehicle(Vehicle vehicle) {
		ResultMsg resultMsg = new ResultMsg();
		/*String sql = "insert into vehicle (originid, destinationid, vehicletype, cost, remark, createtime) VALUES (?, ?, ?, ?, ?, now())";
		int num = vehicleDao.commonUpdate(sql, vehicle.getOriginid(), vehicle.getDestinationid(), vehicle.getCost(), vehicle.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);*/
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg getVehicleById(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select * from vehicle v where v.id = ?";
		Vehicle vehicle = vehicleDao.getJavaBean(sql, Vehicle.class, id);
		if (vehicle != null) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("操作失败！");
			return resultMsg;
		}
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(vehicle);
		return resultMsg;
	}

	@Override
	public ResultMsg deleteVehicle(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete vehicle v where v.id = ?";
		int num = vehicleDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg updateVehicle(Vehicle vehicle) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update vehicle v set v.cost = ?, v.remark = ?, v.updatetime=now() where v.id = ?";
		int num = vehicleDao.commonUpdate(sql, vehicle.getCost(), vehicle.getRemark(), vehicle.getId());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
