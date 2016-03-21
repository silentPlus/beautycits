package com.zyh.beautycits.service.restaurant;

import java.util.List;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.restaurant.RestaurantType;

public interface RestaurantTypeService extends BaseService{
	/**
	 * 
	 * getVehicle(查询所有饭店类别信息)
	 */
	public ResultMsg getRestaurantTypes(Integer currentPage, String name, Integer userid);
	
	/**
	 * 
	 * addTicketType(新增饭店类别)
	 * @date 2016年3月19日 下午2:09:43
	 *
	 */
	public ResultMsg addRestaurantType(RestaurantType restaurantType);
	
	/**
	 * 
	 * deleteTicketType(删除饭店类别)
	 * @date 2016年3月19日 下午2:10:06
	 */
	public ResultMsg deleteRestaurantType(Integer id);
	
	/**
	 * 
	 * getAllTicketType(获取全部饭店类型信息)
	 * @date 2016年3月19日 下午3:42:26
	 */
	public List<RestaurantType> getAllRestaurantType(Integer userid);
}
