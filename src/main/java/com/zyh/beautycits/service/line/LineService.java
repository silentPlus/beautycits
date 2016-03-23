package com.zyh.beautycits.service.line;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.line.Line;

public interface LineService extends BaseService {
	
	/**
	 * 
	 * getLines(获取线路信息)
	 * @date 2016年3月23日 上午10:21:21
	 */
	public ResultMsg getLines(Integer currentPage, String name, Integer lineTypeid, Integer ispublish, Integer num);

	/**
	 * 
	 * saveLine(保存线路信息)
	 */
	public ResultMsg saveLine(Line line);
	
	/**
	 * 
	 * deleteUser(删除线路)
	 */
	public ResultMsg deleteLine(Integer id);

	/**
	 * 
	 * publishLine(发布线路)
	 * 
	 */
	public ResultMsg publishLine(Integer id);

}
