package com.zyh.beautycits.service.restaurant;

import java.util.List;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.restaurant.Restaurant;

public interface RestaurantService extends BaseService {
	/**
	 * 
	 * getRestaurants(查询所有饭店信息)
	 */
	public ResultMsg getRestaurants(Integer currentPage, Integer restauranttypeid, Integer format, Integer userid);
	
	/**
	 * 
	 * addRestaurant(新增饭店信息)
	 * @date 2016年3月19日 下午2:09:43
	 *
	 */
	public ResultMsg addRestaurant(Restaurant restaurant);
	
	/**
	 * 
	 * deleteRestaurant(删除饭店信息)
	 * @date 2016年3月19日 下午2:10:06
	 */
	public ResultMsg deleteRestaurant(Integer id);
	
	/**
	 * 
	 * getAllRestaurant(获取所有饭店信息)
	 */
	public List<Restaurant> getAllRestaurant(Integer userid);
	
}
