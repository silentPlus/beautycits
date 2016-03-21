package com.zyh.beautycits.service.restaurant.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.restaurant.RestaurantTypeService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.restaurant.RestaurantType;

@Service("restaurantTypeService")
public class RestaurantTypeServiceImpl extends BaseServiceImpl implements RestaurantTypeService{
	
	@Autowired
	private JdbcBaseDao<RestaurantType> restaurantTypeDao;
	
	@Override
	public ResultMsg getRestaurantTypes(Integer currentPage, String name, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select * from restauranttype rt where rt.userid = ? ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and rt.name like '%").append(name).append("%' ");
		}
		
		PageInfo<RestaurantType> pageRestaurantType = new PageInfo<>();
		pageRestaurantType.setPageSize(ConfigConstants.PAGESIZE);
		pageRestaurantType.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageRestaurantType = restaurantTypeDao.getPageModel(pageRestaurantType, sql, countsql, RestaurantType.class, userid);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageRestaurantType);
		return resultMsg;
	}

	@Override
	public ResultMsg addRestaurantType(RestaurantType restaurantType) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT INTO restauranttype(userid, name, star, createtime) values(?, ?, ?,now())");
		sql.trimToSize();
		int num = restaurantTypeDao.commonUpdate(sql.toString(), restaurantType.getUserid(), restaurantType.getName(), restaurantType.getStar());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteRestaurantType(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from restauranttype where id = ?";
		int num = restaurantTypeDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public List<RestaurantType> getAllRestaurantType(Integer userid) {
		String sql = "select * from restauranttype where userid = ?";
		List<RestaurantType> list = restaurantTypeDao.getList(sql, RestaurantType.class, userid);
		return list;
	}

}
