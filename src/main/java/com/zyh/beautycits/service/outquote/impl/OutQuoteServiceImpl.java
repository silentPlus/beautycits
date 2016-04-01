package com.zyh.beautycits.service.outquote.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.outquote.OutQuoteService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.quote.OutQuote;

@Service("outQuoteService")
public class OutQuoteServiceImpl extends BaseServiceImpl implements OutQuoteService{

	@Autowired
	private JdbcBaseDao<OutQuote> outQuoteDao;
	
	@Override
	public ResultMsg getOutQuotes(Integer currentPage, Integer lineid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select o.*, l.`name` as linename from outquote o LEFT JOIN line l on l.id = o.lineid ");
		
		if (lineid != null) {
			sql.append(" where o.lineid = ").append(lineid);
		}
		
		PageInfo<OutQuote> pageOutQuote = new PageInfo<>();
		pageOutQuote.setPageSize(ConfigConstants.PAGESIZE);
		pageOutQuote.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		try{
			pageOutQuote = outQuoteDao.getPageModel(pageOutQuote, sql, countsql, OutQuote.class);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageOutQuote);
		return resultMsg;
	}

	@Override
	public ResultMsg updateOutQuote(Integer id, String offercost, String grossprofit, String remark) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update outquote set offercost = ?, grossprofit = ?, remark = ?, updatetime = now() where id = ?";
		int count = outQuoteDao.commonUpdate(sql, offercost, grossprofit, remark, id);
		if (count != 1) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("操作失败！");
			return resultMsg;
		}
		sql = "select * from outquote where id = ?";
		OutQuote outquote = outQuoteDao.getJavaBean(sql, OutQuote.class, id);
		sql = "update line set num = num + 1 where id = ?";
		count = outQuoteDao.commonUpdate(sql, outquote.getLineid());
		if (count != 1) {
			logger.error("网站对外报价修改线路可报价标记失败，sql:" + sql + ";id:" + outquote.getLineid());
		}
		resultMsg.setState(Results.SUCCESS);
		return resultMsg;
	}

}
