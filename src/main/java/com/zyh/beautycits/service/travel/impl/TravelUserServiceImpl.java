package com.zyh.beautycits.service.travel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.travel.TravelUserService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.travel.TravelQuote;
import com.zyh.beautycits.vo.travel.TravelUser;

@Service("travelUserService")
public class TravelUserServiceImpl extends BaseServiceImpl implements TravelUserService{

	@Autowired
	private JdbcBaseDao<TravelQuote> travelQuoteDao;
	
	@Autowired
	private JdbcBaseDao<TravelUser> travelUserDao;
	
	@Override
	public ResultMsg getTravelUsers(Integer linedetailid, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		TravelQuote travelQuote = getTravelQuote(linedetailid, userid);
		if (travelQuote == null) {
			resultMsg.setFlag(0);
		} else {
			resultMsg.setFlag(1);
			String sql = "select * from traveluser tu where tu.travelquoteid = ?";
			List<TravelUser> list = travelUserDao.getList(sql, TravelUser.class, travelQuote.getId());
			resultMsg.setMsgEntity(list);
		}
		return resultMsg;
	}
	
	@Override
	public ResultMsg getTravelUsers(Integer linedetailid, String time) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("select tu.* from traveluser tu ");
		sql.append("where tu.ispublish=1 and tu.linedetailid = ? and tu.time = ? order by tu.createtime");
		List<TravelUser> list = travelUserDao.getList(sql.toString(), TravelUser.class, linedetailid, time);
		resultMsg.setMsgEntity(list);
		return resultMsg;
	}

	@Override
	public ResultMsg addTravelUser(TravelUser travelUser, Integer userid) {
		ResultMsg resultMsg = new ResultMsg();
		TravelQuote travelQuote = getTravelQuote(travelUser.getLinedetailid(), userid);
		if (travelQuote == null) {
			String sql = "INSERT INTO travelquote(userid, linedetailid, num, iscost, createtime) VALUES(:userid,:linedetailid,0,0,now())";
			Map<String, Object> params = new HashMap<String, Object>(2);
			params.put("userid", userid);
			params.put("linedetailid", travelUser.getLinedetailid());
			Number id = travelQuoteDao.updateByMapForkey(sql, params, "id");
			travelUser.setTravelquoteid(id.intValue());
		} else {
			travelUser.setTravelquoteid(travelQuote.getId());
		}
		
		String newsql = "INSERT INTO traveluser(linedetailid, travelquoteid, name, age, ispublish, createtime) VALUES(?,?,?,?,0,now())";
		
		int num = travelUserDao.commonUpdate(newsql, travelUser.getLinedetailid(), travelUser.getTravelquoteid(), travelUser.getName(), travelUser.getAge());
		if (num == 1) {
			newsql = "update travelquote tq set tq.num = tq.num+1, tq.updatetime = now() where tq.id = ?";
			num = travelQuoteDao.commonUpdate(newsql, travelUser.getTravelquoteid());
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}
	
	private TravelQuote getTravelQuote(Integer linedetailid, Integer userid) {
		String sql = "select * from travelquote tq where tq.linedetailid = ? and tq.userid = ? and iscost = 0";
		return travelQuoteDao.getJavaBean(sql, TravelQuote.class, linedetailid, userid);
	}

	@Override
	public ResultMsg delTravelUser(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select * from traveluser where id=?";
		TravelUser travelUser = travelUserDao.getJavaBean(sql, TravelUser.class, id);
		sql = "delete from traveluser where id = ?";
		int num = travelUserDao.commonUpdate(sql, id);
		if (num == 1) {
			sql = "update travelquote tq set tq.num = tq.num-1, tq.updatetime = now() where tq.id = ?";
			num = travelQuoteDao.commonUpdate(sql, travelUser.getTravelquoteid());
			
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}
	@Override
	public ResultMsg addTravelUser(TravelUser travelUser) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select COUNT(*) as truenumber, ld.number from traveluser tu LEFT JOIN linedetail ld on ld.id = tu.linedetailid where tu.ispublish = 1 and tu.time = ? and tu.linedetailid = ?";
		Map<String, Object> map = travelQuoteDao.getMap(sql, travelUser.getTime(), travelUser.getLinedetailid());
		Integer number = Integer.valueOf(map.get("number").toString());
		Integer truenumber = Integer.valueOf(map.get("truenumber").toString());
		if (truenumber >= number) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("该日期出行人数已满，请选择其他出行日期");
			return resultMsg;
		}
		
		String newsql = "INSERT INTO traveluser(linedetailid, name, time, age, ispublish, createtime) VALUES(?,?,?,?,1,now())";
		
		int num = travelUserDao.commonUpdate(newsql, travelUser.getLinedetailid(), travelUser.getName(), travelUser.getTime(), travelUser.getAge());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}
	
}
