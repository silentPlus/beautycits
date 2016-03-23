package com.zyh.beautycits.service.guide;

import java.util.List;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.guide.Guide;

public interface GuideService extends BaseService {
	/**
	 * 
	 * getGuide(查询所有导游信息)
	 *
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg getGuide(Integer currentPage, String guidename, Integer isused, Integer userid);
	
	/**
	 * 
	 * saveGuide(保存导游信息)
	 *
	 */
	public ResultMsg saveGuide(Guide guide);
	
	/**
	 * 
	 * deleteGuide(删除)
	 *
	 * @param id
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg deleteGuide(Integer id);
	
	/**
	 * 
	 * getAllGuide(获取所有可用导游信息)
	 */
	public List<Guide> getAllGuide();
	
	/**
	 * 
	 * updateGuide(可用不可用操作)
	 */
	public ResultMsg updateGuide(Integer id, Integer isused);
}
