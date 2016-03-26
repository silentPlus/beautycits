package com.zyh.beautycits.service.line;

import java.util.Map;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.line.LineDetail;

public interface LineDetailService extends BaseService{
	/**
	 * 
	 * getLineDetails(获取旅行社负责线路信息)
	 * @date 2016年3月23日 上午10:21:21
	 */
	public ResultMsg getLineDetails(Integer currentPage, Integer userid, Integer lineid);
	
	/**
	 * 
	 * saveLineDetail(保存线路信息)
	 */
	public ResultMsg saveLineDetail(LineDetail lineDetail);
	
	/**
	 * 
	 * deleteLineDetail(删除线路)
	 */
	public ResultMsg deleteLineDetail(Integer id);

	/**
	 * 
	 * getLineSchedul(获取线路名称和天数，日程安排时使用)
	 */
	public Map<String, Object> getLineSchedul(Integer id);
}
