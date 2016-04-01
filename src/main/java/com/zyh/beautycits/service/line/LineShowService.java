package com.zyh.beautycits.service.line;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;

public interface LineShowService extends BaseService{
	/**
	 * 
	 * getLinesShow(获取线路信息)
	 * @date 2016年3月23日 上午10:21:21
	 */
	public ResultMsg getLinesShow(Integer currentPage, String name, Integer lineTypeid);
}
