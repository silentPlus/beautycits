package com.zyh.beautycits.service.travel;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;

public interface TravelQuoteService extends BaseService{

	/**
	 * 
	 * getTravelQuotes(获取登录用户的订单信息)
	 */
	public ResultMsg getTravelQuotes(Integer currentPage, Integer userid);
}
