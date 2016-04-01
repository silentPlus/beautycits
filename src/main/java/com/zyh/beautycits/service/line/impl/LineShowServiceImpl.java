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
import com.zyh.beautycits.vo.line.Line;

@Service("lineShowService")
public class LineShowServiceImpl extends BaseServiceImpl implements LineShowService{

	@Autowired
	private JdbcBaseDao<Line> lineDao;
	
	@Override
	public ResultMsg getLinesShow(Integer currentPage, String name, Integer lineTypeid) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("SELECT l.*, lt.name as linetype from line l LEFT JOIN linetype lt on lt.id = l.linetypeid ");
		sql.append(" where l.deleteflg = 0 and l.ispublish = 1");
		
		if (StringUtils.isNotBlank(name)) {
			sql.append("and l.name like '%").append(name).append("%' ");
		}
		if (lineTypeid != null) {
			sql.append("and l.linetypeid = ").append(lineTypeid);
		}
		
		PageInfo<Line> pageLine = new PageInfo<>();
		pageLine.setPageSize(ConfigConstants.PAGESIZE);
		pageLine.setCurrentPage(currentPage);
		StringBuffer countsql = new StringBuffer("select count(*) from (");
		countsql.append(sql).append(") m");
		pageLine = lineDao.getPageModel(pageLine, sql, countsql, Line.class);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageLine);
		return resultMsg;
	}

}
