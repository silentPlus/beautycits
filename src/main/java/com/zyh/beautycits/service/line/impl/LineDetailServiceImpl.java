package com.zyh.beautycits.service.line.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.LineDetailService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.LineDetail;

@Service("lineDetailService")
public class LineDetailServiceImpl extends BaseServiceImpl implements LineDetailService{
	
	@Autowired
	private JdbcBaseDao<LineDetail> lineDetailDao;
	
	@Override
	public ResultMsg getLineDetails(Integer currentPage, Integer userid, Integer lineid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select ld.*, l.name as linename, gv.vehicletype as gtype, bv.vehicletype as btype, g.guidename as guidename ");
		sql.append("from linedetail ld LEFT JOIN line l on l.id = ld.lineid LEFT JOIN vehicle gv on gv.id = ld.govehicleid ");
		sql.append("LEFT JOIN vehicle bv on bv.id = ld.backvehicleid LEFT JOIN guide g on g.id = ld.guideid WHERE ld.userid = ? and ld.deleteflg = 0 ");
		
		if (lineid != null) {
			sql.append("and ld.lineid = ").append(lineid);
		}
		
		PageInfo<LineDetail> pageLineDetail = new PageInfo<>();
		pageLineDetail.setPageSize(ConfigConstants.PAGESIZE);
		pageLineDetail.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		try{
		pageLineDetail = lineDetailDao.getPageModel(pageLineDetail, sql, countsql, LineDetail.class, userid);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageLineDetail);
		return resultMsg;
	}

	@Override
	public ResultMsg saveLineDetail(LineDetail lineDetail) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT into linedetail(userid, lineid, govehicleid, backvehicleid, insurance, guideid, gocost, backcost, guidecost, remark, createtime) ");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,now())");
		sql.trimToSize();
		int num = lineDetailDao.commonUpdate(sql.toString(), lineDetail.getUserid(), lineDetail.getLineid(), lineDetail.getGovehicleid(), lineDetail.getBackvehicleid(),lineDetail.getInsurance(),
				lineDetail.getGuideid(), lineDetail.getGocost(), lineDetail.getBackcost(), lineDetail.getGuidecost(), lineDetail.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteLineDetail(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update linedetail ld set ld.deleteflg = 1, ld.updatetime = now() where ld.id = ?";
		int num = lineDetailDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public Map<String, Object> getLineSchedul(Integer id) {
		String sql = "select l.name as linename, l.day from linedetail ld LEFT JOIN line l on l.id = ld.lineid where ld.id = ?";
		Map<String, Object> map = lineDetailDao.getMap(sql, id);
		return map;
	}

}
