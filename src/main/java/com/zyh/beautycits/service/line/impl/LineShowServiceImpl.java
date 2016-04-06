package com.zyh.beautycits.service.line.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.LineShowService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.LineShow;

@Service("lineShowService")
public class LineShowServiceImpl extends BaseServiceImpl implements LineShowService{

	@Autowired
	private JdbcBaseDao<LineShow> lineShowDao;
	
	@Override
	public ResultMsg getLinesShow(Integer currentPage, String name, Integer lineTypeid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select l.`name` as linename, l.`day` as day, lt.`name` as linetype, gv.vehicletype as govehicleid, bv.vehicletype as backvehicleid, l.updatetime as publishtime from linedetail ld ");
		sql.append("LEFT JOIN line l LEFT JOIN linetype lt on lt.id = l.linetypeid on l.id = ld.lineid ");
		sql.append("LEFT JOIN vehicle gv on gv.id = ld.govehicleid ");
		sql.append("LEFT JOIN vehicle bv on bv.id = ld.backvehicleid ");
		sql.append("where l.deleteflg = 0 and l.ispublish = 1 ");
		if (StringUtils.isNotBlank(name)) {
			sql.append("and l.name like '%").append(name).append("%' ");
		}
		if (lineTypeid != null) {
			sql.append("and l.linetypeid = ").append(lineTypeid);
		}
		
		PageInfo<LineShow> pageLineShow = new PageInfo<>();
		pageLineShow.setPageSize(ConfigConstants.PAGESIZE);
		pageLineShow.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageLineShow = lineShowDao.getPageModel(pageLineShow, sql, countsql, LineShow.class);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageLineShow);
		return resultMsg;
	}

	@Override
	public LineShow getLineDetail(Integer linedetailid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("select l.`name` as linename, l.`day` as day, lt.`name` as linetype, gv.vehicletype as govehicleid, bv.vehicletype as backvehicleid, l.updatetime as publishtime from linedetail ld ");
		sql.append("LEFT JOIN line l LEFT JOIN linetype lt on lt.id = l.linetypeid on l.id = ld.lineid ");
		sql.append("LEFT JOIN vehicle gv on gv.id = ld.govehicleid ");
		sql.append("LEFT JOIN vehicle bv on bv.id = ld.backvehicleid ");
		sql.append("where ld=? ");
		LineShow lineShow = lineShowDao.getJavaBean(sql.toString(), LineShow.class, linedetailid);
		
		return lineShow;
	}

}
