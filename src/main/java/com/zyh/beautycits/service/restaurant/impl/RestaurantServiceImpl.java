package com.zyh.beautycits.service.restaurant.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.restaurant.RestaurantService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.restaurant.Restaurant;

@Service("restaurantService")
public class RestaurantServiceImpl extends BaseServiceImpl implements RestaurantService {
	
	@Autowired
	private JdbcBaseDao<Restaurant> restaurantDao;

	@Override
	public ResultMsg getRestaurants(Integer currentPage, Integer restauranttypeid, Integer format, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select r.*, rt.name as restaurant from restaurant r LEFT JOIN restauranttype rt on rt.id = r.restauranttypeid where r.userid = ?  ");
		if (restauranttypeid != null) {
			sql.append(" and r.restauranttypeid = ").append(restauranttypeid);
		}
		if (format != null) {
			sql.append(" and r.format = ").append(format);
		}
		
		PageInfo<Restaurant> pageRestaurant = new PageInfo<>();
		pageRestaurant.setPageSize(ConfigConstants.PAGESIZE);
		pageRestaurant.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageRestaurant = restaurantDao.getPageModel(pageRestaurant, sql, countsql, Restaurant.class, userid);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageRestaurant);
		return resultMsg;
	}

	@Override
	public ResultMsg addRestaurant(Restaurant restaurant) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT INTO restaurant(userid, provinceid, cityid, areaid, area, restauranttypeid, format, cost, remark, createtime) ");
		sql.append("values(?,?,?,?,?,?,?,?,?,now())");
		sql.trimToSize();
		int num = restaurantDao.commonUpdate(sql.toString(), restaurant.getUserid(), restaurant.getProvinceid(), restaurant.getCityid(), restaurant.getAreaid(), restaurant.getArea(), 
				restaurant.getRestauranttypeid(), restaurant.getFormat(), restaurant.getCost(), restaurant.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteRestaurant(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from restaurant where id = ?";
		int num = restaurantDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
