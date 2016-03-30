package com.zyh.beautycits.service.line.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
		sql.append("values(:userid,:lineid,:govehicleid,:backvehicleid,:insurance,:guideid,:gocost,:backcost,:guidecost,:remark,now())");
		sql.trimToSize();
		Number num = lineDetailDao.updateByBeanForkey(sql.toString(), lineDetail, "id");
		if (num != null) {
			// 新增对内报价数据
			BigDecimal gocost = new BigDecimal(StringUtils.isBlank(lineDetail.getGocost())?"0":lineDetail.getGocost());
			BigDecimal backcost = new BigDecimal(StringUtils.isBlank(lineDetail.getBackcost())?"0":lineDetail.getBackcost());
			BigDecimal guidecost = new BigDecimal(StringUtils.isBlank(lineDetail.getGuidecost())?"0":lineDetail.getGuidecost());
			BigDecimal insurance = new BigDecimal(StringUtils.isBlank(lineDetail.getInsurance())?"0":lineDetail.getInsurance());
			BigDecimal cost = gocost.add(backcost).add(guidecost).add(insurance);
			String ssql = "INSERT INTO innerquote(lineid, linedetailid, primecost, createtime) VALUES(?,?,?,now())";
			int count = lineDetailDao.commonUpdate(ssql,lineDetail.getLineid(), num, cost.toString());
			if (count != 1) {
				logger.error("保存对内报价失败！sql:" + ssql + ", lineid:" + lineDetail.getLineid() + ", linedetailid:" + num + ", cost:" + cost);
			}
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
			sql = "delete from innerquote where linedetailid = ?";
			num = lineDetailDao.commonUpdate(sql, id);
			if (num != 1) {
				logger.error("删除对内报价失败！sql:" + sql + ", linedetailid:" + id);
			}
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
