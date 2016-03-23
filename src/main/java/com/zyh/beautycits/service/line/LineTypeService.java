package com.zyh.beautycits.service.line;

import java.util.List;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.line.LineType;

public interface LineTypeService extends BaseService {
	/**
	 * 
	 * getLineTypes(查询所有线路类别信息)
	 */
	public ResultMsg getLineTypes(Integer currentPage);
	
	/**
	 * 
	 * addLineType(新增线路类别)
	 * @date 2016年3月19日 下午2:09:43
	 *
	 */
	public ResultMsg addLineType(LineType lineType);
	
	/**
	 * 
	 * deleteLineType(删除线路类别)
	 * @date 2016年3月19日 下午2:10:06
	 */
	public ResultMsg deleteLineType(Integer id);
	
	/**
	 * 
	 * getAllLineType(获取全部线路类型信息)
	 * @date 2016年3月19日 下午3:42:26
	 */
	public List<LineType> getAllLineType();
}
