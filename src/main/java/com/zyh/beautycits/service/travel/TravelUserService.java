package com.zyh.beautycits.service.travel;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.travel.TravelUser;

public interface TravelUserService extends BaseService{
	
	/**
	 * 
	 * getTravelUsers(获取linedetailid和userid的游客信息)
	 */
	public ResultMsg getTravelUsers(Integer linedetailid, Integer userid);
	
	/**
	 * 
	 * getTravelUsers(获取同一linedetailid和time的游客信息)
	 */
	public ResultMsg getTravelUsers(Integer linedetailid, String time);
	
	/**
	 * 
	 * addTravelUser(增加游客信息)
	 */
	public ResultMsg addTravelUser(TravelUser travelUser, Integer userid);
	
	/**
	 * 
	 * addTravelUser(网站增加非在线游客信息)
	 */
	public ResultMsg addTravelUser(TravelUser travelUser);
	
	/**
	 * 
	 * delTravelUser(删除游客信息)
	 */
	public ResultMsg delTravelUser(Integer id);
}
