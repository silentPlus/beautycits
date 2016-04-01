package com.zyh.beautycits.service.outquote;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;

public interface OutQuoteService extends BaseService{
	
	/**
	 * 
	 * getInnerQuotes(获取所有报价信息)
	 */
	public ResultMsg getOutQuotes(Integer currentPage, Integer lineid);
	
	/**
	 * 
	 * updateInnerQuote(修改报价单)
	 */
	public ResultMsg updateOutQuote(Integer id, String offercost, String grossprofit, String remark);
	
}
