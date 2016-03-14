package com.zyh.beautycits.service.staff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.staff.VehicleService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.vehicle.Vehicle;

@Service("vehicleService")
public class VehicleServiceImpl extends BaseServiceImpl implements VehicleService{
	
	@Autowired
	private JdbcBaseDao<Vehicle> vehicleDao;
	
	@Override
	public ResultMsg getVehicle(Integer vehicletype, Integer originid, Integer destinationid) {
		StringBuilder sql = new StringBuilder("select v.*,CONCAT(p1.name, c1.name, a1.name) as origin, CONCAT(p2.name, c2.name, a2.name) as destination ")
				.append("from vehicle v, area a1, area a2, city c1, city c2, province p1, province p2 ")
				.append("where v.originid = a1.id and a1.citycode = c1.code and c1.provincecode = p1.code")
				.append("and v.destinationid = a2.id and a2.citycode = c2.code and c2.provincecode = p2.code ");
		if (vehicletype != null) {
			sql.append("and v.vehicletype = ? ");
		}
		if (originid != null) {
			sql.append("and v.originid = ? ");
		}
		if (destinationid != null) {
			sql.append("and destinationid = ? ");
		}
		return null;
	}

	@Override
	public ResultMsg saveVehicle(Vehicle vehicle) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "insert into vehicle (originid, destinationid, vehicletype, cost, remark, createtime) VALUES (?, ?, ?, ?, ?, now())";
		int num = vehicleDao.commonUpdate(sql, vehicle.getOriginid(), vehicle.getDestinationid(), vehicle.getCost(), vehicle.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
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
