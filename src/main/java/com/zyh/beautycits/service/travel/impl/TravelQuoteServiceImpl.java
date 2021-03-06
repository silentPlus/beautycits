package com.zyh.beautycits.service.travel.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.travel.TravelQuoteService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.travel.TravelQuote;

@Service("travelQuoteService")
public class TravelQuoteServiceImpl extends BaseServiceImpl implements TravelQuoteService {

	@Autowired
	private JdbcBaseDao<TravelQuote> travelQuoteDao;
	
	@Override
	public ResultMsg getTravelQuotes(Integer currentPage, Integer userid, Integer iscost) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select tq.*, l.`name` as linename ");
		sql.append("from travelquote tq LEFT JOIN linedetail ld LEFT JOIN line l ON l.id = ld.lineid ON ld.id = tq.linedetailid ");
		sql.append("where tq.userid = ? ");
		
		if (iscost != null) {
			sql.append("and tq.iscost = ").append(iscost);
		}
		sql.append(" ORDER BY tq.iscost, tq.updatetime desc");
		PageInfo<TravelQuote> pageTravelQuote = new PageInfo<>();
		pageTravelQuote.setPageSize(ConfigConstants.PAGESIZE);
		pageTravelQuote.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		try{
			pageTravelQuote = travelQuoteDao.getPageModel(pageTravelQuote, sql, countsql, TravelQuote.class, userid);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageTravelQuote);
		return resultMsg;
	}
	
	@Override
	public ResultMsg getAllTravelQuotes(Integer currentPage, Integer iscost) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select tq.*, l.name as linename from linedetail ld LEFT JOIN line l ON l.id = ld.lineid  ");
		sql.append("LEFT JOIN travelquote tq on tq.linedetailid = ld.id  ");
		sql.append("where tq.iscost <> 0 and ld.deleteflg = 0  ");
		if (iscost != null) {
			sql.append("and tq.iscost = ").append(iscost);
		}
		sql.append(" GROUP BY tq.linedetailid,tq.time order by tq.iscost, tq.updatetime ");
		PageInfo<TravelQuote> pageTravelQuote = new PageInfo<>();
		pageTravelQuote.setPageSize(ConfigConstants.PAGESIZE);
		pageTravelQuote.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		try{
			pageTravelQuote = travelQuoteDao.getPageModel(pageTravelQuote, sql, countsql, TravelQuote.class);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageTravelQuote);
		return resultMsg;
	}
	
	@Override
	public ResultMsg getLsTravelQuotes(Integer currentPage, Integer userid, Integer iscost) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select tq.*, l.name as linename from linedetail ld LEFT JOIN line l ON l.id = ld.lineid  ");
		sql.append("LEFT JOIN travelquote tq on tq.linedetailid = ld.id  ");
		sql.append("where ld.userid=? and tq.iscost in (2,3) and ld.deleteflg = 0  ");
		if (iscost != null) {
			sql.append("and tq.iscost = ").append(iscost);
		}
		sql.append(" GROUP BY tq.linedetailid,tq.time order by tq.iscost, tq.updatetime ");
		PageInfo<TravelQuote> pageTravelQuote = new PageInfo<>();
		pageTravelQuote.setPageSize(ConfigConstants.PAGESIZE);
		pageTravelQuote.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		try{
			pageTravelQuote = travelQuoteDao.getPageModel(pageTravelQuote, sql, countsql, TravelQuote.class, userid);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageTravelQuote);
		return resultMsg;
	}

	@Override
	public ResultMsg quoteTravel(Integer id, String time) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select distinct ld.number, tu.linedetailid from linedetail ld LEFT JOIN traveluser tu on ld.id = tu.linedetailid where tu.travelquoteid = ?";
		Map<String, Object> map = travelQuoteDao.getMap(sql, id);
		Integer linedetailid = Integer.valueOf(map.get("linedetailid").toString());
		Integer truenumber = Integer.valueOf(map.get("number").toString());
		sql = "select count(*) from traveluser tu where tu.ispublish = 1 and tu.time = ? and tu.linedetailid = ?";
		long nownumber = travelQuoteDao.getCount(sql, time, linedetailid);
		if (truenumber <= nownumber) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("该日期出行人数已满，请选择其他出行日期");
			return resultMsg;
		}
		
		sql = "UPDATE travelquote tq set tq.iscost = 1, tq.time=?, tq.updatetime=now() where tq.id=?";
		int num = travelQuoteDao.commonUpdate(sql, time, id);
		if (num == 1) {
			sql = "update traveluser tu set tu.ispublish = 1, tu.time=?, tu.createtime=now() where tu.travelquoteid = ?";
			num = travelQuoteDao.commonUpdate(sql, time, id);
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg delTravelQuote(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete from travelquote where id=?";
		int num = travelQuoteDao.commonUpdate(sql, id);
		if (num == 1) {
			sql = "delete from traveluser where travelquoteid = ?";
			num = travelQuoteDao.commonUpdate(sql, id);
			if (num != 1) {
				logger.error("删除用户报名表信息失败！sql:" + sql + ", travelquoteid:" + id);
			}
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}
	
	@Override
	public ResultMsg publishTravel(Integer linedetailid, String time){
		ResultMsg resultMsg = new ResultMsg();
		String sql = "UPDATE travelquote tq set tq.iscost = 2, tq.updatetime=now() where tq.linedetailid=? and tq.time=?";
		int num = travelQuoteDao.commonUpdate(sql, linedetailid, time);
		if (num >= 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}
	
	@Override
	public ResultMsg finishTravel(Integer linedetailid, String time){
		ResultMsg resultMsg = new ResultMsg();
		String sql = "UPDATE travelquote tq set tq.iscost = 3, tq.updatetime=now() where tq.linedetailid=? and tq.time=?";
		int num = travelQuoteDao.commonUpdate(sql, linedetailid, time);
		if (num >= 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
