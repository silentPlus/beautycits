package com.zyh.beautycits.service.innerquote;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;

public interface InnerQuoteService extends BaseService{
	
	/**
	 * 
	 * getInnerQuotes(获取所有报价信息)
	 */
	public ResultMsg getInnerQuotes(Integer currentPage, Integer lineid, Integer userid);
	
	/**
	 * 
	 * updateInnerQuote(修改报价单)
	 */
	public ResultMsg updateInnerQuote(Integer id, String offercost, String grossprofit, String remark);
	
	/**
	 * 
	 * quoter(报价操作)
	 */
	public ResultMsg quoter(Integer id);
}
