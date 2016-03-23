package com.zyh.beautycits.service.line.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.constats.ConfigConstants;
import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.line.LineService;
import com.zyh.beautycits.vo.PageInfo;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.Line;

@Service("lineService")
public class LineServiceImpl extends BaseServiceImpl implements LineService{
	
	@Autowired
	private JdbcBaseDao<Line> lineDao;

	@Override
	public ResultMsg getLines(Integer currentPage, String name, Integer lineTypeid,  Integer ispublish, Integer num) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer sql = new StringBuffer("SELECT l.*, lt.name as linetype from line l LEFT JOIN linetype lt on lt.id = l.linetypeid ");
		sql.append(" where l.deleteflg = 0 ");
		
		if (StringUtils.isNotBlank(name)) {
			sql.append("and l.name like '%").append(name).append("%' ");
		}
		if (lineTypeid != null) {
			sql.append("and l.linetypeid = ").append(lineTypeid);
		}
		if (num != null && num == 0) {
			sql.append("and l.num = 0 ");
		}
		if (num != null && num > 0) {
			sql.append("and l.num > 0 ");
		}
		if (ispublish != null) {
			sql.append("and l.ispublish = ").append(ispublish);
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

	@Override
	public ResultMsg saveLine(Line line) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("INSERT INTO line(name, linetypeid, day, remark, createtime) values(?,?,?,?,now())");
		sql.trimToSize();
		int num = lineDao.commonUpdate(sql.toString(), line.getName(), line.getLinetypeid(), line.getDay(), line.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteLine(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update line l set l.deleteflag = 1, u.updatetime = now() where l.id = ?";
		int num = lineDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg publishLine(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update line l set l.ispublish = 1, l.updatetime = now() where l.id = ?";
		int num = lineDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public List<Line> getAllLine() {
		String sql = "select * from line l where l.deleteflg = 0 ORDER BY l.createtime desc ";
		List<Line> list = lineDao.getList(sql, Line.class);
		return list;
	}

}
