package com.zyh.beautycits.service.innerquote.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.innerquote.InnerQuoteService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.quote.InnerQuote;

@Service("innerQuoteService")
public class InnerQuoteServiceImpl extends BaseServiceImpl implements InnerQuoteService{
	
	@Autowired
	private JdbcBaseDao<InnerQuote> innerQuoteDao;

	@Override
	public ResultMsg getInnerQuotes(Integer currentPage, Integer lineid, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select i.*, l.name as linename from innerquote i LEFT JOIN line l on l.id = i.lineid LEFT JOIN linedetail ld on ld.id = i.linedetailid ");
		sql.append("where ld.userid = ? ");
		
		if (lineid != null) {
			sql.append(" and i.lineid = ").append(lineid);
		}
		
		PageInfo<InnerQuote> pageInnerQuote = new PageInfo<>();
		pageInnerQuote.setPageSize(ConfigConstants.PAGESIZE);
		pageInnerQuote.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		try{
			pageInnerQuote = innerQuoteDao.getPageModel(pageInnerQuote, sql, countsql, InnerQuote.class, userid);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageInnerQuote);
		return resultMsg;
	}

	@Override
	public ResultMsg updateInnerQuote(Integer id, String offercost, String grossprofit, String remark) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update innerquote set offercost = ?, grossprofit = ?, remark = ?, updatetime = now() where id = ?";
		int count = innerQuoteDao.commonUpdate(sql, offercost, grossprofit, remark, id);
		if (count != 1) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("操作失败！");
			return resultMsg;
		}
		resultMsg.setState(Results.SUCCESS);
		return resultMsg;
	}

	@Override
	public ResultMsg quoter(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update innerquote set isquote = 1, updatetime = now() where id = ?";
		int count = innerQuoteDao.commonUpdate(sql, id);
		if (count != 1) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("操作失败！");
			return resultMsg;
		}
		sql = "select * from innerquote where id=?";
		InnerQuote innerQuote = innerQuoteDao.getJavaBean(sql, InnerQuote.class, id);
		sql = "insert INTO outquote(lineid, linedetailid, primecost, createtime) values(?, ?, ?, now())";
		count = innerQuoteDao.commonUpdate(sql, innerQuote.getLineid(), innerQuote.getLinedetailid(), innerQuote.getOffercost());
		if (count != 1) {
			logger.error("保存outquote表数据失败，innerquote:" + innerQuote.toString());
		}
		resultMsg.setState(Results.SUCCESS);
		return resultMsg;
	}

}
