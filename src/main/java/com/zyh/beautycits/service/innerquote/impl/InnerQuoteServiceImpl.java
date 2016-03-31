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
		StringBuffer sql = new StringBuffer("select * from innerquote i LEFT JOIN line l on l.id = i.lineid LEFT JOIN linedetail ld on ld.id = i.linedetailid ");
		sql.append("where ld.userid = ? ");
		
		if (lineid != null) {
			sql.append(" and i.lineid = ? ").append(lineid);
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
	public ResultMsg updateInnerQuote(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
