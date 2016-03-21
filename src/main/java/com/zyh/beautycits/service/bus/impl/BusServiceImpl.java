package com.zyh.beautycits.service.bus.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.bus.BusService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.bus.Bus;

@Service("busService")
public class BusServiceImpl extends BaseServiceImpl implements BusService{
	
	@Autowired
	private JdbcBaseDao<Bus> busDao;

	@Override
	public ResultMsg getBus(Integer currentPage, Integer bustype, Integer isused, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select * from bus b where b.userid = ? ");
		if (bustype != null) {
			sql.append(" and b.bustype = ").append(bustype);
		}
		if (isused != null) {
			sql.append(" and b.isused = ").append(isused);
		}
		
		PageInfo<Bus> pageBus = new PageInfo<>();
		pageBus.setPageSize(ConfigConstants.PAGESIZE);
		pageBus.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageBus = busDao.getPageModel(pageBus, sql, countsql, Bus.class, userid);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageBus);
		return resultMsg;
	}

	@Override
	public ResultMsg saveBus(Bus bus) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT INTO bus(name, userid, bustype, cost, remark, createtime) values(?,?,?,?,?,now())");
		sql.trimToSize();
		int num = busDao.commonUpdate(sql.toString(), bus.getName(), bus.getUserid(), bus.getBustype(), bus.getCost(), bus.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteBus(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from bus where id = ?";
		int num = busDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
